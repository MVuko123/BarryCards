package com.example.barrycards.prvaGodina

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.barrycards.AddEditKolegiji
import com.example.barrycards.R
import com.google.android.material.floatingactionbutton.FloatingActionButton



class ActivityPrvaGodina : AppCompatActivity() {
    private var prvaGodinaViewModel: PrvaGodinaViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prva_godina)
        val buttonDodajKolegiji = findViewById<FloatingActionButton>(R.id.button_add_kolegiji)


        val uloga = intent.getStringExtra("Uloga")

        if ("Admin" == uloga) {
            buttonDodajKolegiji.visibility = View.VISIBLE
            buttonDodajKolegiji.setOnClickListener {
                val intent = Intent(this@ActivityPrvaGodina, AddEditKolegiji::class.java)
                startActivityForResult(intent, ADD_PRVI_KOLEGIJI_REQUEST)
            }
        } else {
            buttonDodajKolegiji.visibility = View.GONE
        }



        val recyclerViewPrviKolegiji = findViewById<RecyclerView>(R.id.recycler_view_prva_godina)
        recyclerViewPrviKolegiji.layoutManager = LinearLayoutManager(this)
        recyclerViewPrviKolegiji.setHasFixedSize(true)

        val adapterPrvaGodina = PrvaGodinaAdapter()
        recyclerViewPrviKolegiji.adapter = adapterPrvaGodina

        prvaGodinaViewModel = ViewModelProvider(this, TvornicaPrvogViewModela(application))[PrvaGodinaViewModel::class.java]
        prvaGodinaViewModel!!.getSviPrviKolegiji().observe(this
        ) { prviKolegiji -> adapterPrvaGodina.setPrviKolegijiList(prviKolegiji as List<PrvaGodinaKolegiji>) }


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
                    prvaGodinaViewModel?.delete(adapterPrvaGodina.getPrviKolegijiAt(viewHolder.adapterPosition))
                    Toast.makeText(this@ActivityPrvaGodina, "Kolegiji obrisan", Toast.LENGTH_SHORT)
                        .show()
                }
            }).attachToRecyclerView(recyclerViewPrviKolegiji)

            adapterPrvaGodina.setOnItemClickListener(object :
                PrvaGodinaAdapter.OnItemClickListenerPrviKolegiji {
                override fun onItemClickPrviKolegiji(prvaGodinaKolegiji: PrvaGodinaKolegiji?) {
                    Log.d(TAG, "onItemClickPrviKolegiji: prviKolkegiji: $prvaGodinaKolegiji")
                    val intent = Intent(this@ActivityPrvaGodina, AddEditKolegiji::class.java)
                    intent.putExtra(AddEditKolegiji.EXTRA_ID_PRVI_KOLEGIJI, prvaGodinaKolegiji?.id)
                    intent.putExtra(AddEditKolegiji.EXTRA_NAZIV, prvaGodinaKolegiji?.naziv)
                    intent.putExtra(AddEditKolegiji.EXTRA_NOSITELJ, prvaGodinaKolegiji?.nositelj)
                    intent.putExtra(AddEditKolegiji.EXTRA_ECTS, prvaGodinaKolegiji?.ects)
                    startActivityForResult(intent, EDIT_PRVI_KOLEGIJI_REQUEST)

                }
            })
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_PRVI_KOLEGIJI_REQUEST && resultCode == RESULT_OK) {
            val naziv = data!!.getStringExtra(AddEditKolegiji.EXTRA_NAZIV)
            val nositelj = data.getStringExtra(AddEditKolegiji.EXTRA_NOSITELJ)
            val ects = data.getStringExtra(AddEditKolegiji.EXTRA_ECTS)
            val prvaGodinaKolegiji = PrvaGodinaKolegiji(naziv.toString(), nositelj.toString(), "ECTS:" +ects.toString())
            prvaGodinaViewModel?.insert(prvaGodinaKolegiji)
            Toast.makeText(this, "Kolegiji spremljen", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_PRVI_KOLEGIJI_REQUEST && resultCode == RESULT_OK) {
            val id = data!!.getIntExtra(AddEditKolegiji.EXTRA_ID_PRVI_KOLEGIJI, -1)
            if (id == -1) {
                Toast.makeText(this, "Kolegij ne može biti ažuriran", Toast.LENGTH_SHORT).show()
                return
            }
            val naziv = data.getStringExtra(AddEditKolegiji.EXTRA_NAZIV)
            val nositelj = data.getStringExtra(AddEditKolegiji.EXTRA_NOSITELJ)
            val ects = data.getStringExtra(AddEditKolegiji.EXTRA_ECTS)

            val prvaGodinaKolegiji = PrvaGodinaKolegiji(naziv.toString(), nositelj.toString(), ects.toString())
            prvaGodinaViewModel?.update(prvaGodinaKolegiji)
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
                prvaGodinaViewModel?.deleteAll()
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
        const val ADD_PRVI_KOLEGIJI_REQUEST = 1
        const val EDIT_PRVI_KOLEGIJI_REQUEST = 2
        private const val TAG = "ActivityPrvaGodina"
    }
}
