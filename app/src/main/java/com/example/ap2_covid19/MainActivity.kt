package com.example.ap2_covid19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        world_imageButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ActivityCountryList::class.java)
            startActivity(intent)
        })

        brazil_imageButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ActivityStateList::class.java)
            startActivity(intent)
        })
    }
}
