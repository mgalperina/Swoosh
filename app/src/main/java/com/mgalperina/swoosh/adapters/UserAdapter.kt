package com.mgalperina.swoosh.adapters

import android.view.View
import android.view.ViewGroup
import com.mgalperina.swoosh.Model.User
import com.mgalperina.swoosh.R
import kotlinx.android.synthetic.main.item_user_layout.*
/*
class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private lateinit var users: List<User>

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(layoutInflater.inflate(R.layout.item_user_layout, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, index: Int) {
        holder.bindModel(users[index])
    }

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindModel(user: User) {
            nameTxt.text = user.name
            addressTxt.text = user.address.street
            positionTxt.text = "position: ${user.address.geo.lat}, ${user.address.geo.lat}" +
        }
    }
}
*/