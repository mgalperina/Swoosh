package com.mgalperina.swoosh.Controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mgalperina.swoosh.Model.Player
import com.mgalperina.swoosh.Model.User
import com.mgalperina.swoosh.R
import com.mgalperina.swoosh.SwooshApplication
import com.mgalperina.swoosh.Utilities.CountingIdlingResourceService
import com.mgalperina.swoosh.Utilities.EXTRA_PLAYER
import com.mgalperina.swoosh.Utilities.FINISH_ACTIVITY_IDLING_RESOURCE
import com.mgalperina.swoosh.services.ApiService
import com.mgalperina.swoosh.services.SimpleApiService

import kotlinx.android.synthetic.main.activity_finish.*
import kotlinx.android.synthetic.main.item_user_layout.*
import kotlinx.android.synthetic.main.item_user_layout.view.*
import javax.inject.Inject

class FinishActivity : AppCompatActivity() {

    @Inject lateinit var _apiService: ApiService

    private val _idlingResourceService = CountingIdlingResourceService()
    private lateinit var _usersAdapter: UserAdapter
    private lateinit var _userList: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        (application as SwooshApplication).appComponent.inject(this)

        _userList = findViewById(R.id.user_list)
        _usersAdapter = UserAdapter()
        _userList.adapter = _usersAdapter

        setSearchVisualCues()

        startSearch()

    }

    private fun setSearchVisualCues() {
        val player = intent
            .getParcelableExtra<Player>(EXTRA_PLAYER)

        searchLeaguesText.text =
            "Looking for ${player.league} ${player.skill} league near you ..."
    }

    private fun startSearch() {
        _idlingResourceService.increment(FINISH_ACTIVITY_IDLING_RESOURCE)
        _apiService
            .getUsers()
            .subscribe({
                showResults(it)
                _idlingResourceService.decrement(FINISH_ACTIVITY_IDLING_RESOURCE)
            }, {
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT)
            })
    }

    private fun showResults(it: List<User>) {
        _usersAdapter.setUsers(it)
        this.searchLeaguesText.visibility = View.GONE
        this.progressBar.visibility = View.GONE
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
            private val nameTxt = itemView.userName
            private val addressTxt = itemView.userAddress
            private val locationTxt = itemView.userLocation

            fun bindModel(user: User) {
                nameTxt.text = user.name
                addressTxt.text = user.address.street
                locationTxt.text = "position: ${user.address.geo.lat}, ${user.address.geo.lat}"
            }
        }
    }

}