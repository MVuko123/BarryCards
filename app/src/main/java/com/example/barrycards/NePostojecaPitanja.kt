package com.example.barrycards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.barrycards.R

class NePostojecaPitanja : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ne_postojeca_pitanja)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}

