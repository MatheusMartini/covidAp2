package com.example.ap2_covid19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ap2_covid19.classes.States
import kotlinx.android.synthetic.main.activity_details.*

class StateDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val array = this.intent.getParcelableExtra<States>("info")

        place_name.text = array.name
        date.text = array.date
        cases.text = resources.getString(R.string.cases) + " " + array.cases.toString()
        deaths.text = resources.getString(R.string.deaths) + " " + array.deaths.toString()
        recovered.text = resources.getString(R.string.suspects) + " " + array.suspects.toString()
        confirmed.text = resources.getString(R.string.refuses) + " " + array.refuses.toString()
    }
}
