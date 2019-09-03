package com.linya.utils.mvi

import com.linya.utils.dispatcher
import com.linya.utils.interfaces.RenderView
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

typealias Store<Wish,State> = Storage<Wish,State,*,*>
@ExperimentalCoroutinesApi
abstract class Storage<Wish : Any ,State : Any,Effect : Any,News: Any>(
        val renderView : RenderView<Wish,State,News>,
        private val initState : State,
        private val actor: Actor<Wish,State,Effect>,
        private val reducer: Reducer<State,Effect>,
        private val newsPublisher: NewsPublisher<Wish,State,Effect,News>? = null
){
    private val stateChannel = ConflatedBroadcastChannel<State>()
    private val wishChannel = Channel<Pair<Wish,Effect>>()
    private val newsListeners = mutableListOf<NewsListener<News>>()

    interface NewsListener<News> {
        fun processNews(state: News) {}
    }

    init {

        GlobalScope.launch(dispatcher) {
            stateChannel.send(initState)
            stateChannel.openSubscription().consumeEach {
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

            for(pair in wishChannel){
                val newState = reducer.reduce(stateChannel.value,pair.second)
                stateChannel.offer(newState)
                val news = newsPublisher?.invokeNewsPublisher(pair.first,pair.second,newState)
                if(news!=null){
                    newsListeners.forEach {
                        it.processNews(news)
                    }
                }
            }
        }
    }

    fun accept(wish: Wish){
        val emitter = object : Emitter<Effect>{
            override fun send(effect: Effect) {
                GlobalScope.launch(dispatcher) {
                    wishChannel.send(Pair(wish,effect))
                }
            }
        }

        actor.invoke(stateChannel.value,wish,emitter)
    }

    fun close(){
        wishChannel.close()
        stateChannel.close()
    }

    fun addNewsListener(listener: NewsListener<News>){
        newsListeners.add(listener)
    }

}