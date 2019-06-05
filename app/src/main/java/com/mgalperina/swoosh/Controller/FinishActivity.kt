package com.mgalperina.swoosh.Controller

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mgalperina.swoosh.Model.Player
import com.mgalperina.swoosh.Model.User
import com.mgalperina.swoosh.R
import com.mgalperina.swoosh.Utilities.EXTRA_PLAYER
import com.mgalperina.swoosh.Utilities.CountingIdlingResourceService
import com.mgalperina.swoosh.Utilities.FINISH_ACTIVITY_IDLING_RESOURCE
import com.mgalperina.swoosh.services.ApiService
import com.mgalperina.swoosh.services.SimpleApiService

import kotlinx.android.synthetic.main.activity_finish.*
import kotlinx.android.synthetic.main.item_user_layout.*

class FinishActivity : AppCompatActivity() {
    private val _idlingResourceService = CountingIdlingResourceService()
    private val _apiService: ApiService = SimpleApiService()
    private lateinit var _usersAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        _usersAdapter = UserAdapter()
        user_list.adapter = _usersAdapter

        val player = intent.getParcelableExtra<Player>(EXTRA_PLAYER)

        searchLeaguesText.text = "Looking for ${player.league} ${player.skill} league near you ..."

        _idlingResourceService.increment(FINISH_ACTIVITY_IDLING_RESOURCE)
            _apiService
                .getUsers()
                .subscribe({
                    _usersAdapter.setUsers(it)
                }, {
                   Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT)
                })

    }


    inner class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
        private val users: MutableList<User> = mutableListOf()

        override fun getItemCount(): Int {
            return users.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
            return UserViewHolder(layoutInflater.inflate(R.layout.item_user_layout, parent, false))
        }

        override fun onBindViewHolder(holder: UserViewHolder, index: Int) {
            holder.bindModel(users[index])
        }

        fun setUsers(users: List<User>) {
            this.users.addAll(users)
            notifyDataSetChanged()
        }

        inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            fun bindModel(user: User) {
                nameTxt.text = user.name
                addressTxt.text = user.address.street
                positionTxt.text = "position: ${user.address.geo.lat}, ${user.address.geo.lat}"
            }
        }
    }

}
