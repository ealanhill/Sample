package me.ealanhill.smartmomsample

import dagger.Component
import me.ealanhill.smartmomsample.networking.ApiModule
import me.ealanhill.smartmomsample.posts.PostsAdapter
import me.ealanhill.smartmomsample.posts.PostsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApiModule::class,
        ContextModule::class))
interface AppComponent {

    fun inject(postsFragment: PostsFragment)

    fun inject(postsAdapter: PostsAdapter)
}