package me.ealanhill.smartmomsample.networking

import dagger.Module
import dagger.Provides
import me.ealanhill.smartmomsample.detail.PostDetailActionCreator
import me.ealanhill.smartmomsample.posts.PostsActionCreator
import javax.inject.Singleton

@Module
class ApiModule(private val postsApi: Api) {

    private val postsActionCreator: PostsActionCreator
    private val postDetailActionCreator: PostDetailActionCreator

    init {
        postsActionCreator = PostsActionCreator(postsApi)
        postDetailActionCreator = PostDetailActionCreator(postsApi)
    }

    @Provides
    @Singleton
    fun provideApi(): Api = postsApi

    @Provides
    @Singleton
    fun providePostsActionCreator(): PostsActionCreator = postsActionCreator

    @Provides
    @Singleton
    fun providePostDetailActionCreator(): PostDetailActionCreator = postDetailActionCreator
}