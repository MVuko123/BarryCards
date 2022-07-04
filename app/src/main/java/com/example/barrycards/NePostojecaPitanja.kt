package com.example.barrycards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.barrycards.R

class NePostojecaPitanja : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ne_postojeca_pitanja)

        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val actionbar = supportActionBar
        actionbar!!.title="Pitanja u izradi"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        val uloga = intent.getStringExtra("Uloga")
        return uloga.toBoolean()
    }
}

