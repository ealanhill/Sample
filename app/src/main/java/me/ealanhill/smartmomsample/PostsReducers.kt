package me.ealanhill.smartmomsample

import me.ealanhill.smartmomsample.detail.PostDetailAction
import me.ealanhill.smartmomsample.networking.model.Post
import me.ealanhill.smartmomsample.posts.AddPostsAction
import me.ealanhill.smartmomsample.posts.RetrievePostsAction
import me.tatarka.redux.Reducer
import me.tatarka.redux.Reducers

object PostsReducers {

    fun reducers(): Reducer<Action, PostsState> = Reducers.matchClass<Action, PostsState>()
            .`when`(RetrievePostsAction::class.java, addPosts())
            .`when`(PostDetailAction::class.java, updatePostDetail())
            .`when`(AddPostsAction::class.java, updatePosts())

    fun addPosts(): Reducer<RetrievePostsAction, PostsState> =
        Reducer { action, state ->
            val posts = state.posts
            state.copy(posts = posts.plus(action.posts).plus(Post(type = Post.Type.LOADING)))
        }

    fun updatePostDetail(): Reducer<PostDetailAction, PostsState> =
            Reducer { action, state ->
                state.copy(postDetail = action.postDetail)
            }

    fun updatePosts(): Reducer<AddPostsAction, PostsState> =
            Reducer { action, state ->
                val posts = state.posts
                posts.drop(posts.size - 1)
                state.copy(posts = posts.plus(action.posts).plus(Post(type = Post.Type.LOADING)))
            }
}