package me.ealanhill.smartmomsample.networking

import dagger.Module
import dagger.Provides
import me.ealanhill.smartmomsample.posts.PostsActionCreator
import javax.inject.Singleton

@Module
class ApiModule(private val postsApi: Api) {

    private val contactsActionCreator: PostsActionCreator

    init {
        contactsActionCreator = PostsActionCreator(postsApi)
    }

    @Provides
    @Singleton
    fun provideApi(): Api = postsApi

    @Provides
    @Singleton
    fun provideContactsActionCreatore(): PostsActionCreator = contactsActionCreator
}