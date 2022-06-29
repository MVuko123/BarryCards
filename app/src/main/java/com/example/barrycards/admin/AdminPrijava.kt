package com.example.barrycards.admin

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.barrycards.KreiranjeBaze
import com.example.barrycards.OdabirActivity
import com.example.barrycards.R

class AdminPrijava : AppCompatActivity() {
    var email: EditText? = null
    var lozinka: EditText? = null
    var prijava: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_prijava)

        email = findViewById(R.id.edit_email)
        lozinka = findViewById(R.id.edit_lozinka)
        prijava = findViewById(R.id.button_prijava)
        prijava!!.setOnClickListener(View.OnClickListener {
            val emailText = email!!.text.toString()
            val lozinkaText = lozinka!!.text.toString()

            if(emailText.isEmpty() || lozinkaText.isEmpty()){
                Toast.makeText(applicationContext, "Unesite potrebne podatke", Toast.LENGTH_SHORT).show()
            }else{
                val kreiranjeBaze = KreiranjeBaze.getInstance(applicationContext as Application?)
                val adminBaza: AdminBaza? = kreiranjeBaze.adminBaza()
                Thread{
                    val admin: Admin? = adminBaza?.prijava(emailText, lozinkaText)
                    if(admin == null){
                        runOnUiThread{
                            Toast.makeText(applicationContext, "Krivo uneseni podaci", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        val uloga: String = admin.uloga
                        startActivity(Intent(this@AdminPrijava, OdabirActivity::class.java)
                            .putExtra("Uloga", uloga))
                    }
                }.start()
            }

        })

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}