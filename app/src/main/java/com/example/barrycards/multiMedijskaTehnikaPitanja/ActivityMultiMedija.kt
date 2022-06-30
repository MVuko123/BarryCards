package com.example.barrycards.multiMedijskaTehnikaPitanja

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.barrycards.AddEditPitanja
import com.example.barrycards.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ActivityMultiMedija : AppCompatActivity() {
    private var multiMedijaViewModel: MultiMedijskaViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_multi_medija)

        val uloga = intent.getStringExtra("Uloga")

        val buttonDodajPitanje = findViewById<FloatingActionButton>(R.id.button_add_pitanje)

        if ("Admin" == uloga ) {
            buttonDodajPitanje.visibility = View.VISIBLE
            buttonDodajPitanje.setOnClickListener {
                val intent = Intent(this@ActivityMultiMedija, AddEditPitanja::class.java)
                startActivityForResult(intent, ADD_MULTI_PITANJA_REQUEST)
            }
        } else {
            buttonDodajPitanje.visibility = View.GONE
        }


        val recyclerViewMultiMedija = findViewById<RecyclerView>(com.example.barrycards.R.id.recycler_view_multi_medija)
        recyclerViewMultiMedija.layoutManager = LinearLayoutManager(this)
        recyclerViewMultiMedija.setHasFixedSize(true)

        val adapterMultiMedija = MultiMedijaAdapter()
        recyclerViewMultiMedija.adapter = adapterMultiMedija



        multiMedijaViewModel = ViewModelProvider(this, TvornicaMMViewModela(application))[MultiMedijskaViewModel::class.java]
        multiMedijaViewModel!!.getSvaMMPitanja().observe(this
        ) { multiMedijskaPitnja -> adapterMultiMedija.setMultiMedijaList(multiMedijskaPitnja as List<MultiMedijskaPitanja>) }


        if ("Admin" == uloga) {
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder,
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    multiMedijaViewModel?.delete(adapterMultiMedija.getMultiMedijaAt(viewHolder.adapterPosition))
                    Toast.makeText(this@ActivityMultiMedija, "Pitanje obrisano", Toast.LENGTH_SHORT)
                        .show()
                }
            }).attachToRecyclerView(recyclerViewMultiMedija)


            adapterMultiMedija.setOnItemClickListener(object :
            MultiMedijaAdapter.OnItemClickListenerMultiMedijskaPitanja{
                override fun onItemClickMultiMedijska(multiMedijskaPitanja: MultiMedijskaPitanja) {
                    Log.d(TAG, "onItemClickMultiMedijska: multiMedijskaPitnja: $multiMedijskaPitanja")
                    val intent = Intent(this@ActivityMultiMedija, AddEditPitanja::class.java)
                    intent.putExtra(AddEditPitanja.EXTRA_ID_PRVA_PITANJA, multiMedijskaPitanja.id)
                    intent.putExtra(AddEditPitanja.EXTRA_PITANJE, multiMedijskaPitanja.pitanje)
                    intent.putExtra(AddEditPitanja.EXTRA_ODGOVOR, multiMedijskaPitanja.odgovor)
                    startActivityForResult(intent, EDIT_MULTI_PITANJA_REQUEST)
                }
            })
        }


        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val actionbar = supportActionBar
        actionbar!!.title="Multimedija Pitanja"
        actionbar.setDisplayHomeAsUpEnabled(true)



    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        val uloga = intent.getStringExtra("Uloga")
        return uloga.toBoolean()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_MULTI_PITANJA_REQUEST && resultCode == RESULT_OK) {
            val pitanje = data!!.getStringExtra(AddEditPitanja.EXTRA_PITANJE)
            val odgovor = data.getStringExtra(AddEditPitanja.EXTRA_ODGOVOR)
            val multiMedijaPitanja = MultiMedijskaPitanja(pitanje.toString(), odgovor.toString())
            multiMedijaViewModel?.insert(multiMedijaPitanja)
            Toast.makeText(this, "Pitanje spremljeno", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_MULTI_PITANJA_REQUEST && resultCode == RESULT_OK) {
            val id = data!!.getIntExtra(AddEditPitanja.EXTRA_ID_PRVA_PITANJA, -1)
            if (id == -1) {
                Toast.makeText(this, "Pitanje ne može biti ažurirano", Toast.LENGTH_SHORT).show()
                return
            }
            val pitanje = data.getStringExtra(AddEditPitanja.EXTRA_PITANJE)
            val odgovor = data.getStringExtra(AddEditPitanja.EXTRA_ODGOVOR)

            val multiMedijaPitanja = MultiMedijskaPitanja(pitanje.toString(), odgovor.toString())
            multiMedijaViewModel?.update(multiMedijaPitanja)
            Toast.makeText(this, "Pitanje je ažurirano", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Pitanje nije spremljeno", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val uloga = intent.getStringExtra("Uloga")
        return if ("Admin" == uloga) {
            val menuInflater = menuInflater
            menuInflater.inflate(R.menu.delete_all_kolegij_menu, menu)
            return true
        } else {
            false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val uloga = intent.getStringExtra("Uloga")
        return if ("Admin" == uloga) {
            return when (item.itemId) {
                R.id.delete_all_kolegiji -> {
                    multiMedijaViewModel?.deleteAll()
                    Toast.makeText(this, "Sva pitanja obrisana", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        } else {
            false
        }
    }

    companion object {
        const val ADD_MULTI_PITANJA_REQUEST = 1
        const val EDIT_MULTI_PITANJA_REQUEST = 2
        private const val TAG = "ActivityMultiMedija"
    }

    /*fun okreni(){
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
    }*/
    }
