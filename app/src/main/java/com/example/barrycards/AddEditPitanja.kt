package com.example.barrycards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast

class AddEditPitanja : AppCompatActivity() {
    private var editPitanje: EditText? = null
    private var editOdgovor: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_pitanja)

        editPitanje = findViewById(R.id.edit_pitanje)
        editOdgovor = findViewById(R.id.edit_odgovor)

        val intent = intent
        if(intent.hasExtra(EXTRA_ID_PRVA_PITANJA)){
            val actionbar = supportActionBar
            actionbar!!.title="Uredite Pitanje"
            actionbar.setDisplayHomeAsUpEnabled(true)
            with(editPitanje){this?.setText(intent.getStringExtra(EXTRA_PITANJE))}
            with(editOdgovor){this?.setText(intent.getStringExtra(EXTRA_ODGOVOR))}
        }else{
            val actionbar = supportActionBar
            actionbar!!.title="Dodajte Pitanje"
            actionbar.setDisplayHomeAsUpEnabled(true)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        val uloga = intent.getStringExtra("Uloga")
        return uloga.toBoolean()
    }

    private fun savePitanje() {
        val pitanje = editPitanje!!.text.toString()
        val odgovor = editOdgovor!!.text.toString()
        if (pitanje.trim { it <= ' ' }.isEmpty() || odgovor.trim { it <= ' ' }
                .isEmpty() )
        {
            Toast.makeText(this, "Unesite tražene podatke", Toast.LENGTH_SHORT).show()
            return
        }
        val data = Intent()
        data.putExtra(EXTRA_PITANJE, pitanje)
        data.putExtra(EXTRA_ODGOVOR, odgovor)
        val id = intent.getIntExtra(EXTRA_ID_PRVA_PITANJA, -1)
        if (id != -1) {
            data.putExtra(EXTRA_ID_PRVA_PITANJA, id)
        }
        setResult(RESULT_OK, data)
        finish()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_kolegiji_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            R.id.save_kolegiji -> {
                savePitanje()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_ID_PRVA_PITANJA = "com.example.barrycards.EXTRA_ID_PRVA_PITANJA"
        const val EXTRA_PITANJE = "com.example.barrycards.EXTRA_PITANJE"
        const val EXTRA_ODGOVOR = "com.example.barrycards.EXTRA_ODGOVOR"
    }
}