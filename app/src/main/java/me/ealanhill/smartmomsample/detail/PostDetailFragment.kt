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
import me.ealanhill.smartmomsample.networking.model.Details
import me.ealanhill.smartmomsample.posts.PostsViewModel

class PostDetailFragment : Fragment() {

    private lateinit var postsViewModel: PostsViewModel
    private lateinit var store: PostsStore

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        postsViewModel = ViewModelProviders.of(activity)
                .get(PostsViewModel::class.java)
        store = postsViewModel.store
        val binding = DataBindingUtil.inflate<FragmentPostDetailBinding>(inflater, R.layout.fragment_post_detail, container, false)
        bindPostDetails(binding, store.state.postDetail)
        postsViewModel.state.observe(this, Observer { data ->
            data?.let { bindPostDetails(binding, data.postDetail) }
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun bindPostDetails(binding: FragmentPostDetailBinding, details: Details) {
        binding.apply {
            detailMessage.text = details.message
            detailPoster.text = details.poster.getName()
            detailDate.text = details.getDate()
            if (details.pictures.isEmpty()) {
                detailImage.visibility = ImageView.GONE
            } else {
                GlideApp.with(activity)
                        .load(details.pictures[0].url)
                        .into(detailImage)
            }
        }
    }
}