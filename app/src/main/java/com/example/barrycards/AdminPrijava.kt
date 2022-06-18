package com.example.barrycards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AdminPrijava : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_prijava)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}