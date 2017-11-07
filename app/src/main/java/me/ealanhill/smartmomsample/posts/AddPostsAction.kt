package me.ealanhill.smartmomsample.posts

import me.ealanhill.smartmomsample.Action
import me.ealanhill.smartmomsample.networking.model.Post

object AddPostsAction: Action {
    var posts: List<Post> = listOf()

    fun create(posts: List<Post>): AddPostsAction {
        RetrievePostsAction.posts = posts
        return this
    }
}