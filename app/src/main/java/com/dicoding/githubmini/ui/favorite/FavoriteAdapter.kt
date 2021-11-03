package com.dicoding.githubmini.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.dicoding.githubmini.api.UserFavorite
import com.dicoding.githubmini.databinding.ItemRowUserBinding
import java.util.*

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.UserViewHolder>() {
    private val listUser = ArrayList<UserFavorite>()
    private var onItemClickCallback: OnItemClickCallback? = null


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(users: ArrayList<UserFavorite>) {
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }

    class UserViewHolder(val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserFavorite) {
            binding.apply {
                tvItemName.text = user.login
                tvItemHtmlurl.text = user.html_Url
                Glide.with(itemView)
                    .load(user.avatar_Url)
                    .transform(CircleCrop())
                    .into(imgItemAvatar)
            }

        }

    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            ItemRowUserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUser[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(listUser[position])
        }
    }

    override fun getItemCount(): Int = listUser.size


    interface OnItemClickCallback {
        fun onItemClicked(data: UserFavorite)

    }
}