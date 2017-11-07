package me.ealanhill.smartmomsample

import me.ealanhill.smartmomsample.posts.RetrievePostsAction
import me.tatarka.redux.Reducer
import me.tatarka.redux.Reducers

object PostsReducers {

    fun reducers(): Reducer<Action, PostsState> = Reducers.matchClass<Action, PostsState>()
            .`when`(RetrievePostsAction::class.java, addPosts())

    fun addPosts(): Reducer<RetrievePostsAction, PostsState> {
        return Reducer { action, state ->
            val posts = state.posts
            state.copy(posts = posts.plus(action.posts))
        }
    }
}