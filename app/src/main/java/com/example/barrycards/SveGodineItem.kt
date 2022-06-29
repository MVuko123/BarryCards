package com.example.barrycards

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import com.example.barrycards.multiMedijskaTehnikaPitanja.ActivityMultiMedija
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SveGodineItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sva_pitanja_item)

        val buttonOkreni = findViewById<AppCompatImageButton>(R.id.button_okreni)
        val pitanja = findViewById<TextView>(R.id.view_pitanje)
        val odgovor = findViewById<TextView>(R.id.view_odgovor)

        buttonOkreni.setOnClickListener{
            if(pitanja.visibility == View.VISIBLE){
                pitanja.visibility = View.INVISIBLE
            }else if(pitanja.visibility == View.INVISIBLE){
                pitanja.visibility = View.VISIBLE
            }

            if(odgovor.visibility == View.VISIBLE){
                odgovor.visibility = View.INVISIBLE
            }else if(odgovor.visibility == View.INVISIBLE){
                odgovor.visibility = View.VISIBLE
            }
        }
    }
}



