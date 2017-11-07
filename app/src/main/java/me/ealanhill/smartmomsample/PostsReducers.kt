package me.ealanhill.smartmomsample

import me.ealanhill.smartmomsample.detail.PostDetailAction
import me.ealanhill.smartmomsample.posts.RetrievePostsAction
import me.tatarka.redux.Reducer
import me.tatarka.redux.Reducers

object PostsReducers {

    fun reducers(): Reducer<Action, PostsState> = Reducers.matchClass<Action, PostsState>()
            .`when`(RetrievePostsAction::class.java, addPosts())
            .`when`(PostDetailAction::class.java, updatePostDetail())

    fun addPosts(): Reducer<RetrievePostsAction, PostsState> =
        Reducer { action, state ->
            val posts = state.posts
            state.copy(posts = posts.plus(action.posts))
        }

    fun updatePostDetail(): Reducer<PostDetailAction, PostsState> =
            Reducer { action, state ->
                state.copy(postDetail = action.postDetail)
            }
}