package com.mgalperina.swoosh.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mgalperina.swoosh.Model.Player
import com.mgalperina.swoosh.R
import com.mgalperina.swoosh.Utilities.EXTRA_PLAYER
import com.mgalperina.swoosh.Utilities.CountingIdlingResourceService

import kotlinx.android.synthetic.main.activity_finish.*

const val IDLING_RESOURCE = "finish_activity"

class FinishActivity : AppCompatActivity() {
    private val idlingResourceService = CountingIdlingResourceService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val player = intent.getParcelableExtra<Player>(EXTRA_PLAYER)

        searchLeaguesText.text = "Looking for ${player.league} ${player.skill} league near you ..."

        idlingResourceService.increment(IDLING_RESOURCE)
        runOnUiThread {
            // apiService.findUsers()
            idlingResourceService.decrement(IDLING_RESOURCE)


            JsonObjectRequest
        }
    }
}
