package me.ealanhill.smartmomsample.posts

import android.util.Log
import me.ealanhill.smartmomsample.Action
import me.ealanhill.smartmomsample.networking.Api
import me.ealanhill.smartmomsample.networking.model.Post
import me.ealanhill.smartmomsample.networking.model.Posts
import me.tatarka.redux.Thunk

class PostsActionCreator(private val postsApi: Api) {

    /**
     * Retrieves the contact list from the server
     */
    fun retrieveContacts(): Thunk<Action, Action> {
        return Thunk { dispatcher ->
            postsApi.getPosts()
                    .enqueue(object : retrofit2.Callback<Posts> {
                        override fun onFailure(call: retrofit2.Call<Posts>?, t: Throwable?) {
                            Log.e("ContactsActionCreator", "Unable to retrieve contacts list", t)
                        }

                        override fun onResponse(call: retrofit2.Call<Posts>?,
                                                response: retrofit2.Response<Posts>?) {
                            response?.apply {
                                dispatcher.dispatch(RetrievePostsAction.create(body()!!.posts))
                            }
                        }
                    })
        }
    }
}