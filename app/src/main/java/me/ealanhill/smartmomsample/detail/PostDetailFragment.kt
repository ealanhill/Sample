package me.ealanhill.smartmomsample.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import me.ealanhill.smartmomsample.GlideApp
import me.ealanhill.smartmomsample.PostsStore
import me.ealanhill.smartmomsample.R
import me.ealanhill.smartmomsample.databinding.FragmentPostDetailBinding
import me.ealanhill.smartmomsample.networking.model.Post
import me.ealanhill.smartmomsample.posts.PostsViewModel

class PostDetailFragment : Fragment() {

    private lateinit var contactsViewModel: PostsViewModel
    private lateinit var store: PostsStore

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        contactsViewModel = ViewModelProviders.of(activity)
                .get(PostsViewModel::class.java)
        store = contactsViewModel.store
        val binding = DataBindingUtil.inflate<FragmentPostDetailBinding>(inflater, R.layout.fragment_post_detail, container, false)
        bindContactDetails(binding, store.state.postDetail)
        contactsViewModel.state.observe(this, Observer { data ->
            data?.let { bindContactDetails(binding, data.postDetail) }
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun bindContactDetails(binding: FragmentPostDetailBinding, post: Post) {
        binding.apply {
            detailMessage.text = post.message
            detailPoster.text = post.poster.getName()
            detailDate.text = post.getDate()
            if (post.pictures.isEmpty()) {
                detailImage.visibility = ImageView.GONE
            } else {
                GlideApp.with(activity)
                        .load(post.pictures[0].url)
                        .into(detailImage)
            }
        }
    }
}