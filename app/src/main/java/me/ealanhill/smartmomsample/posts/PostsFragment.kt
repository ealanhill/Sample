package me.ealanhill.smartmomsample.posts

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.ealanhill.smartmomsample.App
import me.ealanhill.smartmomsample.MainActivity
import me.ealanhill.smartmomsample.PostsStore
import me.ealanhill.smartmomsample.R
import me.ealanhill.smartmomsample.databinding.FragmentPostsBinding
import me.ealanhill.smartmomsample.detail.PostDetailAction
import me.ealanhill.smartmomsample.detail.PostDetailActionCreator
import me.ealanhill.smartmomsample.detail.PostDetailFragment
import me.ealanhill.smartmomsample.networking.model.Post
import javax.inject.Inject


class PostsFragment : Fragment(), PostsAdapter.PostsOnClickListener {

    companion object {
        private val LAYOUT_MANAGER = "RECYCLER_LAYOUT_MANAGER"
    }

    private lateinit var binding: FragmentPostsBinding
    private lateinit var contactsViewModel: PostsViewModel
    private lateinit var store: PostsStore

    @Inject
    lateinit var actionCreator: PostsActionCreator

    @Inject
    lateinit var postDetailActionCreator: PostDetailActionCreator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactsViewModel = ViewModelProviders.of(activity)
                .get(PostsViewModel::class.java)
        store = contactsViewModel.store
        App.COMPONENT.inject(this)
        store.dispatch(actionCreator.retrievePosts())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            binding.postsRecyclerView
                    .layoutManager
                    .onRestoreInstanceState(savedInstanceState.getParcelable(LAYOUT_MANAGER))
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentPostsBinding>(inflater, R.layout.fragment_posts, container, false)
                .apply {
                    postsRecyclerView.layoutManager = LinearLayoutManager(context)
                    postsRecyclerView.adapter = PostsAdapter(
                            store.state.posts,
                            this@PostsFragment,
                            object : PostsAdapter.OnBottomReachedListener{
                                override fun onBottomReached(post: Post) {
                                    actionCreator.retrievePosts(post.uuid)
                                }
                            })
                }

        contactsViewModel.state.observe(this, Observer { data ->
            data?.let { (binding.postsRecyclerView.adapter as PostsAdapter).setData(data.posts) }
        })
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(LAYOUT_MANAGER,
                binding.postsRecyclerView.layoutManager.onSaveInstanceState())
    }

    override fun onClick(post: Post) {
        store.dispatch(postDetailActionCreator.retrievePostDetail(post.uuid))
        (activity as MainActivity).swapFragments(PostDetailFragment(), addToBackStack = true)
    }
}
