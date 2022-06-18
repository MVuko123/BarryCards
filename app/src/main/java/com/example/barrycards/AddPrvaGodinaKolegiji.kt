package com.example.barrycards

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class AddPrvaGodinaKolegiji : AppCompatActivity() {
    private var editNaziv: EditText? = null
    private var editNositelj: EditText? = null
    private var editECTS: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.barrycards.R.layout.activity_add_prva_godina_kolegiji)
        editNaziv = findViewById(com.example.barrycards.R.id.edit_naziv)
        editNositelj = findViewById(com.example.barrycards.R.id.edit_nositelj)
        editECTS = findViewById(com.example.barrycards.R.id.edit_ECTS)

        val intent = intent
        if (intent.hasExtra(EXTRA_ID_PRVI_KOLEGIJI)) {
            title = "Uredite kolegiji"
            with(editNaziv) { this?.setText(intent.getStringExtra(EXTRA_NAZIV)) }
            with(editNositelj) { this?.setText(intent.getStringExtra(EXTRA_NOSITELJ)) }
            with(editECTS) { this?.setText(intent.getStringExtra(EXTRA_ECTS)) }
        } else {
            title = "Dodajte kolegiji"
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun savePrviKolegiji() {
        val naziv = editNaziv!!.text.toString()
        val nositelj = editNositelj!!.text.toString()
        val ects = editECTS!!.text.toString()
        if (naziv.trim { it <= ' ' }.isEmpty() || nositelj.trim { it <= ' ' }
                .isEmpty() || ects.trim { it <= ' ' }
                .isEmpty()) {
            Toast.makeText(this, "Unesite tražene podatke", Toast.LENGTH_SHORT).show()
            return
        }
        val data = Intent()
        data.putExtra(EXTRA_NAZIV, naziv)
        data.putExtra(EXTRA_NOSITELJ, nositelj)
        data.putExtra(EXTRA_ECTS, ects)
        val id = intent.getIntExtra(EXTRA_ID_PRVI_KOLEGIJI, -1)
        if (id != -1) {
            data.putExtra(EXTRA_ID_PRVI_KOLEGIJI, id)
        }
        setResult(RESULT_OK, data)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(com.example.barrycards.R.menu.add_prvi_kolegiji_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            com.example.barrycards.R.id.save_prvi_kolegiji -> {
                savePrviKolegiji()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_ID_PRVI_KOLEGIJI = "com.example.barrycards.EXTRA_ID_ZADATAK"
        const val EXTRA_NAZIV = "com.example.barrycards.EXTRA_NAZIV"
        const val EXTRA_NOSITELJ = "com.example.barrycards.EXTRA_NOSITELJ"
        const val EXTRA_ECTS = "com.example.barrycards.EXTRA_ECTS"
    }


}

