package com.example.bai6.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bai6.R
import com.example.bai6.data.model.ApiUser
import com.example.bai6.databinding.ItemListUsersBinding
import com.example.bai6.util.loadImage

class UserListAdapter(private val onItemClick: (ApiUser.User) -> Unit) :
    ListAdapter<ApiUser.User, UserListAdapter.UserViewHolder>(DiffCallback) {
    class UserViewHolder(private val binding: ItemListUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: ApiUser.User) {
            binding.image.loadImage(user.picture.medium)
            binding.tvName.text = user.name.toString()
            binding.tvId.text = user.id.toString()
            binding.tvLocation.text = user.location.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemListUsersBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_list_users, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClick(current)
        }
        holder.bind(current)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ApiUser.User>() {
            override fun areItemsTheSame(oldItem: ApiUser.User, newItem: ApiUser.User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ApiUser.User, newItem: ApiUser.User): Boolean {
                return oldItem.name == newItem.name &&
                        oldItem.dob == newItem.dob &&
                        oldItem.cell == newItem.cell &&
                        oldItem.email == newItem.email &&
                        oldItem.gender == newItem.gender &&
                        oldItem.location == newItem.location &&
                        oldItem.nat == newItem.nat &&
                        oldItem.login == newItem.login &&
                        oldItem.phone == newItem.phone &&
                        oldItem.picture == newItem.picture &&
                        oldItem.registered == newItem.registered
            }

        }
    }
}