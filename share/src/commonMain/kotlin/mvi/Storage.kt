package com.linya.utils.mvi

import com.linya.utils.dispatcher
import com.linya.utils.interfaces.RenderView
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach


typealias Store<Wish,State> = Storage<Wish,State,*,*>
@ExperimentalCoroutinesApi
abstract class Storage<Wish : Any ,State : Any,Effect : Any,News: Any>(
        val renderView : RenderView<Wish,State>,
        private val initState : State,
        private val actor: Actor<Wish,State,Effect>,
        private val reducer: Reducer<State,Effect>
){
    private val subject = ConflatedBroadcastChannel<State>()
    private val channel = Channel<Effect>()

    init {

        GlobalScope.launch(dispatcher) {
            subject.send(initState)
            subject.openSubscription().consumeEach {
                this@Storage.renderView.render(it)
            }
        }

    }

    interface Emitter<T>{
        fun send(effect: T)
    }

    interface Reducer<State,Effect>{
         fun reduce(state: State,effect: Effect): State
    }

    interface Actor<Wish,State,Effect>{
        fun invoke(state: State, wish: Wish, channel : Emitter<Effect>)
    }

    init {

        GlobalScope.launch(dispatcher) {

            for(eff in channel){
                val newState = reducer.reduce(subject.value,eff)
                subject.send(newState)
            }
        }
    }

    fun accept(wish: Wish){
        val emitter = object : Emitter<Effect>{
            override fun send(effect: Effect) {
                GlobalScope.launch(dispatcher) {
                    channel.send(effect)
                }
            }
        }

        actor.invoke(subject.value,wish,emitter)
    }

}