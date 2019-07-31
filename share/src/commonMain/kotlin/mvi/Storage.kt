package mvi

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import ribs.RenderView
import utils.MyScope
import utils.dispatcher
import utils.ui.Screen

@ExperimentalCoroutinesApi
abstract class Storage<Wish,State,Effect>(
    val screen : Screen<Wish,State>,
    private val initState : State,
    private val actor: Actor<Wish,State,Effect>,
    private val reducer: Reducer<State,Effect>
) {
    private val subject = ConflatedBroadcastChannel<State>()
    private val channel = Channel<Effect>()

    init {

        GlobalScope.launch(dispatcher) {
            subject.send(initState)
        }

    }

    fun activate(){
        //this.renderView = renderView
        GlobalScope.launch(dispatcher) {
            subject.openSubscription().consumeEach {
                this@Storage.screen.renderView.render(it)
            }
        }
    }
    fun deactivate(){
        //this.renderView  = null
    }

    interface PresenterListener<State>{
        fun render(state: State)
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
        println("accept !" + wish)
        val emitter = object : Emitter<Effect>{
            override fun send(effect: Effect) {
                println("effect !" + effect)
                GlobalScope.launch(dispatcher) {
                    channel.send(effect)
                }


            }
        }

        actor.invoke(subject.value,wish,emitter)
    }

}