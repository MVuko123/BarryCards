package com.example.barrycards

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.barrycards.drugaGodina.ActivityDrugaGodina
import com.example.barrycards.prvaGodina.ActivityPrvaGodina
import com.example.barrycards.trecaGodina.ActivityTrecaGodina


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

        val drugaGodina = findViewById<Button>(R.id.buttonDrugaGodina)
        drugaGodina.setOnClickListener{
            val Intent = Intent(this, ActivityDrugaGodina::class.java)
            startActivity(Intent)
        }

        val trecaGodina = findViewById<Button>(R.id.buttonTrecaGodina)
        trecaGodina.setOnClickListener{
            val Intent = Intent(this, ActivityTrecaGodina::class.java)
            startActivity(Intent)
        }

    }
}