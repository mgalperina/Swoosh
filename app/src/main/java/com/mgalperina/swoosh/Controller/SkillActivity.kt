package com.mgalperina.swoosh.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.mgalperina.swoosh.Utilities.EXTRA_LEAGUE
import com.mgalperina.swoosh.R
import com.mgalperina.swoosh.Utilities.EXTRA_SKILL
import kotlinx.android.synthetic.main.activity_skill.*

class SkillActivity : AppCompatActivity() {

    var league = ""
    var skill = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skill)
        league = intent.getStringExtra(EXTRA_LEAGUE)
    }

    fun onBallerClicked(view: View) {
        beginnerClickBtn.isChecked = false
        skill = "baller"
    }

    fun onBeginnerClicked(view: View) {
        ballerClickBtn.isChecked = false
        skill = "beginner"
    }

    fun onSkillFinishClicked(view: View) {
        if (skill != "" ) {
            val finishActivity = Intent(this, FinishActivity::class.java)
            finishActivity.putExtra(EXTRA_LEAGUE, league)
            finishActivity.putExtra(EXTRA_SKILL, skill)
            startActivity(finishActivity)
        } else {
            Toast.makeText(this, "Please select a skill level", Toast.LENGTH_SHORT).show()
        }

    }


}
