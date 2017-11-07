package me.ealanhill.smartmomsample.detail

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import me.ealanhill.smartmomsample.App
import me.ealanhill.smartmomsample.GlideApp
import me.ealanhill.smartmomsample.R
import me.ealanhill.smartmomsample.databinding.LayoutCommentBinding
import me.ealanhill.smartmomsample.networking.model.Comment
import javax.inject.Inject

class PostDetailAdapter(private var comments: List<Comment>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        App.COMPONENT.inject(this)
    }

    @Inject
    lateinit var context: Context

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val postHolder = holder as CommentViewHolder
        if (comments.isNotEmpty()) {
            postHolder.bind(
                    comment = comments[position],
                    context = context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CommentViewHolder(
                inflater.inflate(R.layout.layout_comment, parent, false) as ConstraintLayout)
    }

    override fun getItemCount(): Int = comments.size

    class CommentViewHolder(itemView: ConstraintLayout): RecyclerView.ViewHolder(itemView) {

        private val binding: LayoutCommentBinding = DataBindingUtil.bind(itemView)

        fun bind(comment: Comment, context: Context?) {
            binding.apply {
                if (comment.pictures.isEmpty()) {
                    commentImage.visibility = ImageView.GONE
                } else {
                    GlideApp.with(context)
                            .load(comment.pictures[0].url)
                            .into(commentImage)
                }
                commentMessage.text = comment.message
                commenterName.text = comment.poster.getName()
                commentDate.text = comment.created
            }
        }
    }
}