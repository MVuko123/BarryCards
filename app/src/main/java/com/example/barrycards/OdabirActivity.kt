package com.example.barrycards

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.barrycards.admin.AdminPrijava
import com.example.barrycards.drugaGodina.ActivityDrugaGodina
import com.example.barrycards.prvaGodina.ActivityPrvaGodina
import com.example.barrycards.trecaGodina.ActivityTrecaGodina


class OdabirActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_odabir)

        val uloga = intent.getStringExtra("Uloga")
        val adminPrijava = findViewById<Button>(R.id.buttonPrijava)

        if(uloga == "Admin"){
            adminPrijava.text = "Odjava"
            adminPrijava.setOnClickListener {
                val Intent = Intent(this, AdminPrijava::class.java)
                startActivity(Intent)
            }
        }else {
            adminPrijava.text = "Admin prijava"
            adminPrijava.setOnClickListener {
                val Intent = Intent(this, AdminPrijava::class.java)
                startActivity(Intent)
            }
        }

       val prvaGodina = findViewById<Button>(R.id.buttonPrvaGodina)
        prvaGodina.setOnClickListener{
            val Intent = Intent(this, ActivityPrvaGodina::class.java).putExtra("Uloga", uloga)
            startActivity(Intent)
        }

        val drugaGodina = findViewById<Button>(R.id.buttonDrugaGodina)
        drugaGodina.setOnClickListener{
            val Intent = Intent(this, ActivityDrugaGodina::class.java).putExtra("Uloga", uloga)
            startActivity(Intent)
        }

        val trecaGodina = findViewById<Button>(R.id.buttonTrecaGodina)
        trecaGodina.setOnClickListener{
            val Intent = Intent(this, ActivityTrecaGodina::class.java).putExtra("Uloga", uloga)
            startActivity(Intent)
        }

    }
}