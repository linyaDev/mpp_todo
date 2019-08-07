package com.linya.utils.mvi

import com.linya.utils.dispatcher
import com.linya.utils.interfaces.RenderView
import com.linya.utils.interfaces.RouterDependencies
import com.linya.utils.interfaces.ViewType
import com.linya.utils.router.Router
import com.linya.utils.ui.todo.TodoMainStorage
import com.linya.utils.ui.todo.TodoRouter
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlin.reflect.KClass


typealias Store<Wish,State> = Storage<Wish,State,*,*>
@ExperimentalCoroutinesApi
abstract class Storage<Wish : Any ,State : Any,Effect : Any,News: Any>(
        val renderView : RenderView<Wish,State,News>,
        private val initState : State,
        private val actor: Actor<Wish,State,Effect>,
        private val reducer: Reducer<State,Effect>,
        private val newsPublisher: NewsPublisher<Wish,State,Effect,News>? = null
){
    private val subject = ConflatedBroadcastChannel<State>()
    private val channel = Channel<Pair<Wish,Effect>>()

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

    interface NewsPublisher<Wish,State,Effect,News> {
        fun invokeNewsPublisher(p1: Wish, p2: Effect, p3: State): News?
    }

    init {

        GlobalScope.launch(dispatcher) {

            for(pair in channel){
                val newState = reducer.reduce(subject.value,pair.second)
                subject.send(newState)
                val news = newsPublisher?.invokeNewsPublisher(pair.first,pair.second,newState)
                if(news!=null){
                    renderView.processNews(news)
                }
            }
        }
    }

    fun accept(wish: Wish){
        val emitter = object : Emitter<Effect>{
            override fun send(effect: Effect) {
                GlobalScope.launch(dispatcher) {
                    channel.send(Pair(wish,effect))
                }
            }
        }

        actor.invoke(subject.value,wish,emitter)
    }

}