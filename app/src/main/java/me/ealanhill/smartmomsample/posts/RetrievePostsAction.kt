package me.ealanhill.smartmomsample.posts

import me.ealanhill.smartmomsample.Action
import me.ealanhill.smartmomsample.networking.model.Post

object RetrievePostsAction: Action {
    var posts: List<Post> = listOf()

    fun create(posts: List<Post>): RetrievePostsAction {
        RetrievePostsAction.posts = posts
        return this
    }
}