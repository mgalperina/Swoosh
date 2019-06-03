package com.mgalperina.swoosh.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.mgalperina.swoosh.Model.Player
import com.mgalperina.swoosh.Model.User
import com.mgalperina.swoosh.R
import com.mgalperina.swoosh.Utilities.EXTRA_PLAYER
import com.mgalperina.swoosh.Utilities.CountingIdlingResourceService
import com.mgalperina.swoosh.Utilities.FINISH_ACTIVITY_IDLING_RESOURCE
import com.mgalperina.swoosh.services.ApiService
import com.mgalperina.swoosh.services.SimpleApiService

import kotlinx.android.synthetic.main.activity_finish.*
import rx.android.schedulers.AndroidSchedulers

class FinishActivity : AppCompatActivity() {
    private val _idlingResourceService = CountingIdlingResourceService()
    private val _apiService: ApiService = SimpleApiService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val player = intent.getParcelableExtra<Player>(EXTRA_PLAYER)

        searchLeaguesText.text = "Looking for ${player.league} ${player.skill} league near you ..."

        _idlingResourceService.increment(FINISH_ACTIVITY_IDLING_RESOURCE)
            _apiService
                .getUsers()
                .subscribe({

                }, {

                })

    }


    inner class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
        private lateinit var users: List<User>

        override fun getItemCount(): Int {
            return users.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
            return UserViewHolder(layoutInflater.inflate(R.layout.user_item_layout, parent, false))
        }

        override fun onBindViewHolder(holder: UserViewHolder, index: Int) {
            holder.bindModel(users[index])
        }

        inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            fun bindModel(user: User) {

            }
        }
    }

}
