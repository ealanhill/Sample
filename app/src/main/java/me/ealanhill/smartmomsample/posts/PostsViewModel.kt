package me.ealanhill.smartmomsample.posts

import android.app.Application
import me.ealanhill.smartmomsample.PostsState
import me.ealanhill.smartmomsample.PostsStore
import me.tatarka.redux.android.lifecycle.StoreAndroidViewModel

class PostsViewModel(application: Application): StoreAndroidViewModel<PostsState, PostsStore>(application, PostsStore())