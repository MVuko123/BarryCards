package com.example.barrycards

import android.R
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_odabir.*


class OdabirActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.barrycards.R.layout.activity_odabir)

        val adminPrijava = findViewById<Button>(com.example.barrycards.R.id.buttonPrijava)
        adminPrijava.setOnClickListener{
            val Intent = Intent(this, AdminPrijava::class.java)
            startActivity(Intent)
        }

       val prvaGodina = findViewById<Button>(com.example.barrycards.R.id.buttonPrvaGodina)
        prvaGodina.setOnClickListener{
            val Intent = Intent(this, ActivityPrvaGodina::class.java)
            startActivity(Intent)
        }

    }
}