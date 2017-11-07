package me.ealanhill.smartmomsample.posts

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import me.ealanhill.smartmomsample.App
import me.ealanhill.smartmomsample.GlideApp
import me.ealanhill.smartmomsample.R
import me.ealanhill.smartmomsample.databinding.LayoutPostBinding
import me.ealanhill.smartmomsample.networking.model.Post
import javax.inject.Inject

class PostsAdapter(private var posts: List<Post>,
                   private val onClickListener: PostsOnClickListener,
                   private val bottomListener: OnBottomReachedListener):
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        App.COMPONENT.inject(this)
    }

    @Inject
    lateinit var context: Context

    interface PostsOnClickListener {
        fun onClick(post: Post)
    }

    interface OnBottomReachedListener {
        fun onBottomReached(post: Post)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val postHolder = holder as PostViewHolder
        if (position == posts.size - 2) {
            bottomListener.onBottomReached(posts[position])
        }
        if (posts.isNotEmpty()) {
            postHolder.bind(
                    post = posts[position],
                    context = context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PostViewHolder(
                inflater.inflate(R.layout.layout_post, parent, false) as ConstraintLayout,
                onClickListener = onClickListener)
    }

    override fun getItemCount(): Int = posts.size

    fun setData(newContacts: List<Post>) {
        if (newContacts != posts) {
            posts = newContacts
            notifyDataSetChanged()
        }
    }

    class PostViewHolder(itemView: ConstraintLayout, val onClickListener: PostsOnClickListener):
            RecyclerView.ViewHolder(itemView) {

        private val binding: LayoutPostBinding = DataBindingUtil.bind(itemView)

        fun bind(post: Post, context: Context?) {
            if (post.type == Post.Type.LOADING) {
                binding.apply {
                    postImage.visibility = ImageView.GONE
                    postMessage.visibility = TextView.GONE
                    posterName.visibility = TextView.GONE
                    progressBar.visibility = ProgressBar.VISIBLE
                }
            } else {
                binding.apply {
                    postImage.visibility = ImageView.VISIBLE
                    postMessage.visibility = TextView.VISIBLE
                    posterName.visibility = TextView.VISIBLE
                    progressBar.visibility = ProgressBar.GONE

                    if (post.pictures.isEmpty()) {
                        postImage.visibility = ImageView.GONE
                    } else {
                        GlideApp.with(context)
                                .load(post.pictures[0].url)
                                .into(postImage)
                    }
                    postMessage.text = post.message
                    posterName.text = post.poster.getName()
                }
                binding.root.setOnClickListener { _ -> onClickListener.onClick(post) }
            }
        }
    }
}