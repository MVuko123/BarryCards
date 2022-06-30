package com.example.barrycards.drugaGodina

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
import com.example.barrycards.AddEditKolegiji
import com.example.barrycards.NePostojecaPitanja
import com.example.barrycards.R
import com.example.barrycards.javaPitanja.ActivityJava
import com.example.barrycards.programiranje1Pitanja.ActivityProgramiranje1
import com.example.barrycards.prvaGodina.ActivityPrvaGodina
import com.example.barrycards.prvaGodina.PrvaGodinaAdapter
import com.example.barrycards.prvaGodina.PrvaGodinaKolegiji
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActivityDrugaGodina : AppCompatActivity() {
    private var drugaGodinaViewModel: DrugaGodinaViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_druga_godina)
        val buttonDodajKolegiji = findViewById<FloatingActionButton>(R.id.button_add_kolegiji)

        val uloga = intent.getStringExtra("Uloga")

        if ("Admin" == uloga) {
        buttonDodajKolegiji.visibility = View.VISIBLE
        buttonDodajKolegiji.setOnClickListener {
            val intent = Intent(this@ActivityDrugaGodina, AddEditKolegiji::class.java)
            startActivityForResult(intent, ADD_DRUGI_KOLEGIJI_REQUEST)
        }
        } else {
            buttonDodajKolegiji.visibility = View.GONE
        }



        val recyclerViewDrugiKolegiji = findViewById<RecyclerView>(R.id.recycler_view_druga_godina)
        recyclerViewDrugiKolegiji.layoutManager = LinearLayoutManager(this)
        recyclerViewDrugiKolegiji.setHasFixedSize(true)

        val adapterDrugaGodina = DrugaGodinaAdapter()
        recyclerViewDrugiKolegiji.adapter = adapterDrugaGodina

        drugaGodinaViewModel = ViewModelProvider(this, TvornicaDrugogViewModela(application))[DrugaGodinaViewModel::class.java]
        drugaGodinaViewModel!!.getSviDrugiKolegiji().observe(this
        ) { drugiKolegiji -> adapterDrugaGodina.setDrugiKolegijiList(drugiKolegiji as List<DrugaGodinaKolegiji>) }


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
                drugaGodinaViewModel?.delete(adapterDrugaGodina.getDrugiKolegijiAt(viewHolder.adapterPosition))
                Toast.makeText(this@ActivityDrugaGodina, "Kolegiji obrisan", Toast.LENGTH_SHORT)
                    .show()
            }
        }).attachToRecyclerView(recyclerViewDrugiKolegiji)

        /*adapterDrugaGodina.setOnItemClickListener(object :
            DrugaGodinaAdapter.OnItemClickListenerDrugiKolegiji {
            override fun onItemClickDrugiKolegiji(drugaGodinaKolegiji: DrugaGodinaKolegiji?) {
                Log.d(TAG, "onItemClickPrviKolegiji: prviKolkegiji: $drugaGodinaKolegiji")
                val intent = Intent(this@ActivityDrugaGodina, AddEditKolegiji::class.java)
                intent.putExtra(AddEditKolegiji.EXTRA_ID_PRVI_KOLEGIJI, drugaGodinaKolegiji?.id)
                intent.putExtra(AddEditKolegiji.EXTRA_NAZIV, drugaGodinaKolegiji?.naziv)
                intent.putExtra(AddEditKolegiji.EXTRA_NOSITELJ, drugaGodinaKolegiji?.nositelj)
                intent.putExtra(AddEditKolegiji.EXTRA_ECTS, drugaGodinaKolegiji?.ects)
                startActivityForResult(intent, EDIT_DRUGI_KOLEGIJI_REQUEST)

            }
        })*/
        }

        adapterDrugaGodina.setOnItemClickListener(object :
            DrugaGodinaAdapter.OnItemClickListenerDrugiKolegiji{
            override fun onItemClickDrugiKolegiji(drugaGodinaKolegiji: DrugaGodinaKolegiji?) {
                Log.d(TAG, "onItemClickDrugiKolegiji: drugiKolegiji: $drugaGodinaKolegiji")

                if (drugaGodinaKolegiji?.id == 1) {
                    val intent = Intent(this@ActivityDrugaGodina, ActivityJava::class.java)
                    intent.putExtra(AddEditKolegiji.EXTRA_ID_PRVI_KOLEGIJI, drugaGodinaKolegiji.id)
                    intent.putExtra("Uloga", uloga)
                    startActivity(intent)
                } else if (drugaGodinaKolegiji?.id == 2) {
                    val intent = Intent(this@ActivityDrugaGodina, NePostojecaPitanja::class.java)
                    intent.putExtra(AddEditKolegiji.EXTRA_ID_PRVI_KOLEGIJI, drugaGodinaKolegiji.id)
                    intent.putExtra("Uloga", uloga)
                    startActivity(intent)
                } else if (drugaGodinaKolegiji?.id == 3) {
                    val intent = Intent(this@ActivityDrugaGodina, NePostojecaPitanja::class.java)
                    intent.putExtra(AddEditKolegiji.EXTRA_ID_PRVI_KOLEGIJI, drugaGodinaKolegiji.id)
                    intent.putExtra("Uloga", uloga)
                    startActivity(intent)
                } else if (drugaGodinaKolegiji?.id == 4) {
                    val intent = Intent(this@ActivityDrugaGodina, NePostojecaPitanja::class.java)
                    intent.putExtra(AddEditKolegiji.EXTRA_ID_PRVI_KOLEGIJI, drugaGodinaKolegiji.id)
                    intent.putExtra("Uloga", uloga)
                    startActivity(intent)
                } else {
                    val intent = Intent(this@ActivityDrugaGodina, NePostojecaPitanja::class.java);
                    startActivity(intent)
                }
            }
        })
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val actionbar = supportActionBar
        actionbar!!.title="Druga Godina Kolegiji"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        val uloga = intent.getStringExtra("Uloga")
        return uloga.toBoolean()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_DRUGI_KOLEGIJI_REQUEST && resultCode == RESULT_OK) {
            val naziv = data!!.getStringExtra(AddEditKolegiji.EXTRA_NAZIV)
            val nositelj = data.getStringExtra(AddEditKolegiji.EXTRA_NOSITELJ)
            val ects = data.getStringExtra(AddEditKolegiji.EXTRA_ECTS)
            val drugaGodinaKolegiji = DrugaGodinaKolegiji(naziv.toString(), nositelj.toString(), "ECTS:" +ects.toString())
            drugaGodinaViewModel?.insert(drugaGodinaKolegiji)
            Toast.makeText(this, "Kolegiji spremljen", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_DRUGI_KOLEGIJI_REQUEST && resultCode == RESULT_OK) {
            val id = data!!.getIntExtra(AddEditKolegiji.EXTRA_ID_PRVI_KOLEGIJI, -1)
            if (id == -1) {
                Toast.makeText(this, "Kolegij ne može biti ažuriran", Toast.LENGTH_SHORT).show()
                return
            }
            val naziv = data.getStringExtra(AddEditKolegiji.EXTRA_NAZIV)
            val nositelj = data.getStringExtra(AddEditKolegiji.EXTRA_NOSITELJ)
            val ects = data.getStringExtra(AddEditKolegiji.EXTRA_ECTS)

            val drugaGodinaKolegiji = DrugaGodinaKolegiji(naziv.toString(), nositelj.toString(), ects.toString())
            drugaGodinaViewModel?.update(drugaGodinaKolegiji)
            Toast.makeText(this, "Kolegij je ažuriran", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Kolegiji nije spremljen", Toast.LENGTH_SHORT).show()
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
                drugaGodinaViewModel?.deleteAll()
                Toast.makeText(this, "Svi kolegiji obrisani", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        } else {
            false
        }
    }

    companion object {
        const val ADD_DRUGI_KOLEGIJI_REQUEST = 1
        const val EDIT_DRUGI_KOLEGIJI_REQUEST = 2
        private const val TAG = "ActivityDrugaGodina"
    }
    }
