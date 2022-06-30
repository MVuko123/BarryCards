package com.example.barrycards.programiranje1Pitanja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.barrycards.AddEditPitanja
import com.example.barrycards.R
import com.example.barrycards.multiMedijskaTehnikaPitanja.MultiMedijaAdapter
import com.example.barrycards.multiMedijskaTehnikaPitanja.MultiMedijskaPitanja
import com.example.barrycards.multiMedijskaTehnikaPitanja.MultiMedijskaViewModel
import com.example.barrycards.multiMedijskaTehnikaPitanja.TvornicaMMViewModela
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActivityProgramiranje1 : AppCompatActivity() {
    private var programiranje1ViewModel: Programiranje1ViewModel?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programiranje1)

        val uloga = intent.getStringExtra("Uloga")

        val buttonDodajPitanje = findViewById<FloatingActionButton>(R.id.button_add_pitanje)

        if ("Admin" == uloga ) {
            buttonDodajPitanje.visibility = View.VISIBLE
            buttonDodajPitanje.setOnClickListener {
                val intent = Intent(this@ActivityProgramiranje1, AddEditPitanja::class.java)
                startActivityForResult(intent, ADD_MULTI_PITANJA_REQUEST)
            }
        } else {
            buttonDodajPitanje.visibility = View.GONE
        }


        val recyclerViewProgramiranje1 = findViewById<RecyclerView>(R.id.recylcer_view_programiranje_1)
        recyclerViewProgramiranje1.layoutManager = LinearLayoutManager(this)
        recyclerViewProgramiranje1.setHasFixedSize(true)

        val adapterProgramiranje1 = Programiranje1Adapter()
        recyclerViewProgramiranje1.adapter = adapterProgramiranje1



        programiranje1ViewModel = ViewModelProvider(this, TvornicaP1ViewModela(application))[Programiranje1ViewModel::class.java]
        programiranje1ViewModel!!.getSvaP1Pitanja().observe(this
        ) { programiranje1Pitanja -> adapterProgramiranje1.setProgramiranje1List(programiranje1Pitanja as List<Programiranje1Pitanja>) }


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
                    programiranje1ViewModel?.delete(adapterProgramiranje1.getProgramiranje1At(viewHolder.adapterPosition))
                    Toast.makeText(this@ActivityProgramiranje1, "Pitanje obrisano", Toast.LENGTH_SHORT)
                        .show()
                }
            }).attachToRecyclerView(recyclerViewProgramiranje1)


            adapterProgramiranje1.setOnItemClickListener(object :
                Programiranje1Adapter.OnItemClickListenerProgramiranje1Pitanja{
                override fun onItemClickProgramiranje1(programiranje1Pitanja: Programiranje1Pitanja) {
                    Log.d(TAG, "onItemClickProgramiranje1: programiranje1Pitanja: $programiranje1Pitanja")
                    val intent = Intent(this@ActivityProgramiranje1, AddEditPitanja::class.java)
                    intent.putExtra(AddEditPitanja.EXTRA_ID_PRVA_PITANJA, programiranje1Pitanja.id)
                    intent.putExtra(AddEditPitanja.EXTRA_PITANJE, programiranje1Pitanja.pitanje)
                    intent.putExtra(AddEditPitanja.EXTRA_ODGOVOR, programiranje1Pitanja.odgovor)
                    startActivityForResult(intent, EDIT_MULTI_PITANJA_REQUEST)
                }
            })
        }


        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val actionbar = supportActionBar
        actionbar!!.title="Programiranje 1 Pitanja"
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
            val programiranje1Pitanja = Programiranje1Pitanja(pitanje.toString(), odgovor.toString())
            programiranje1ViewModel?.insert(programiranje1Pitanja)
            Toast.makeText(this, "Pitanje spremljeno", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_MULTI_PITANJA_REQUEST && resultCode == RESULT_OK) {
            val id = data!!.getIntExtra(AddEditPitanja.EXTRA_ID_PRVA_PITANJA, -1)
            if (id == -1) {
                Toast.makeText(this, "Pitanje ne može biti ažurirano", Toast.LENGTH_SHORT).show()
                return
            }
            val pitanje = data.getStringExtra(AddEditPitanja.EXTRA_PITANJE)
            val odgovor = data.getStringExtra(AddEditPitanja.EXTRA_ODGOVOR)

            val programiranje1Pitanja = Programiranje1Pitanja(pitanje.toString(), odgovor.toString())
            programiranje1ViewModel?.update(programiranje1Pitanja)
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
                    programiranje1ViewModel?.deleteAll()
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
        private const val TAG = "ActivityProgramiranje1"
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
