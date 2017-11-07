package me.ealanhill.smartmomsample.detail

import android.util.Log
import me.ealanhill.smartmomsample.Action
import me.ealanhill.smartmomsample.networking.Api
import me.ealanhill.smartmomsample.networking.model.PostDetail
import me.tatarka.redux.Thunk

class PostDetailActionCreator(private val postsApi: Api) {

    fun retrievePostDetail(uuid: String): Thunk<Action, Action> {
        return Thunk { dispatcher ->
            postsApi.getPostDetail(uuid)
                    .enqueue(object : retrofit2.Callback<PostDetail> {
                        override fun onFailure(call: retrofit2.Call<PostDetail>?, t: Throwable?) {
                            Log.e("ContactsActionCreator", "Unable to retrieve contacts list", t)
                        }

                        override fun onResponse(call: retrofit2.Call<PostDetail>?,
                                                response: retrofit2.Response<PostDetail>?) {
                            response?.apply {
                                dispatcher.dispatch(PostDetailAction(body()!!.details))
                            }
                        }
                    })
        }
    }
}