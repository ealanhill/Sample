package me.ealanhill.smartmomsample

import me.tatarka.redux.Dispatcher
import me.tatarka.redux.SimpleStore
import me.tatarka.redux.Thunk
import me.tatarka.redux.ThunkDispatcher

class PostsStore : SimpleStore<PostsState>(PostsState()) {
    val dispatcher: Dispatcher<Action, Action> = Dispatcher.forStore(this, PostsReducers.reducers())
    val thunkDispatcher: Dispatcher<Thunk<Action, Action>, Void> = ThunkDispatcher(dispatcher)

    fun dispatch(action: Action): Action = dispatcher.dispatch(action)

    fun dispatch(thunk: Thunk<Action, Action>) = thunkDispatcher.dispatch(thunk)
}