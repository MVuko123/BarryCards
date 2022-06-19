package com.example.barrycards

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton



class ActivityPrvaGodina : AppCompatActivity() {
    private var prvaGodinaViewModel: PrvaGodinaViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prva_godina)
        val buttonDodajZadatak = findViewById<FloatingActionButton>(R.id.button_add_kolegiji)


        //val uloga = intent.getStringExtra("Uloga")

        //if ("Admin" == uloga || "Superuser" == uloga) {
            buttonDodajZadatak.visibility = View.VISIBLE
            buttonDodajZadatak.setOnClickListener {
                val intent = Intent(this@ActivityPrvaGodina, AddPrvaGodinaKolegiji::class.java)
                startActivityForResult(intent, ADD_PRVI_KOLEGIJI_REQUEST)
            }
        /*} else {
            buttonDodajZadatak.visibility = View.GONE
        }*/
        val recyclerViewPrviKolegiji = findViewById<RecyclerView>(R.id.recycler_view_prva_godina)
        recyclerViewPrviKolegiji.layoutManager = LinearLayoutManager(this)
        recyclerViewPrviKolegiji.setHasFixedSize(true)
        val adapterPrvaGodina = PrvaGodinaAdapter()
        recyclerViewPrviKolegiji.adapter = adapterPrvaGodina

        prvaGodinaViewModel = ViewModelProvider(this, TvornicaViewModela(application))[PrvaGodinaViewModel::class.java]
        prvaGodinaViewModel!!.getSviPrviKolegiji().observe(this, object : Observer<List<PrvaGodinaKolegiji?>?> {
            override fun onChanged(prviKolegiji: List<PrvaGodinaKolegiji?>?) {
                adapterPrvaGodina.setPrviKolegijiList(prviKolegiji as List<PrvaGodinaKolegiji>)
            }
        })
       // if ("Admin" == uloga) {
           /* ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    recyclerViewPrviKolegiji.delete(adapterPrvaGodina.getZadaciAt(viewHolder.adapterPosition))
                    Toast.makeText(this@ActivityZadaci, "Zadatak obrisan", Toast.LENGTH_SHORT)
                        .show()
                }
            }).attachToRecyclerView(recyclerViewZadaci) */
        adapterPrvaGodina.setOnItemClickListener(object : PrvaGodinaAdapter.OnItemClickListenerPrviKolegiji {
            override fun onItemClickPrviKolegiji(prvaGodinaKolegiji: PrvaGodinaKolegiji?) {
                Log.d(TAG, "onItemClickPrviKolegiji: prviKolkegiji: $prvaGodinaKolegiji")
                val intent = Intent(this@ActivityPrvaGodina, AddPrvaGodinaKolegiji::class.java)
                    intent.putExtra(AddPrvaGodinaKolegiji.EXTRA_ID_PRVI_KOLEGIJI, prvaGodinaKolegiji?.id)
                    intent.putExtra(AddPrvaGodinaKolegiji.EXTRA_NAZIV, prvaGodinaKolegiji?.naziv)
                    intent.putExtra(AddPrvaGodinaKolegiji.EXTRA_NOSITELJ, prvaGodinaKolegiji?.nositelj)
                    intent.putExtra(AddPrvaGodinaKolegiji.EXTRA_ECTS, prvaGodinaKolegiji?.ects)
                    startActivityForResult(intent, EDIT_PRVI_KOLEGIJI_REQUEST)

            }
        })
        //}
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_PRVI_KOLEGIJI_REQUEST && resultCode == RESULT_OK) {
            val naziv = data!!.getStringExtra(AddPrvaGodinaKolegiji.EXTRA_NAZIV)
            val nositelj = data.getStringExtra(AddPrvaGodinaKolegiji.EXTRA_NOSITELJ)
            val ects = data.getStringExtra(AddPrvaGodinaKolegiji.EXTRA_ECTS)
            val prvaGodinaKolegiji = PrvaGodinaKolegiji(naziv.toString(), nositelj.toString(), ects.toString())
            prvaGodinaViewModel?.insert(prvaGodinaKolegiji)
            Toast.makeText(this, "Kolegiji spremljen", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_PRVI_KOLEGIJI_REQUEST && resultCode == RESULT_OK) {
            val id = data!!.getIntExtra(AddPrvaGodinaKolegiji.EXTRA_ID_PRVI_KOLEGIJI, -1)
            if (id == -1) {
                Toast.makeText(this, "Kolegij ne može biti ažuriran", Toast.LENGTH_SHORT).show()
                return
            }
            /*val naziv = data.getStringExtra(AddPrvaGodinaKolegiji.EXTRA_NAZIV)
            val nositelj = data.getStringExtra(AddPrvaGodinaKolegiji.EXTRA_NOSITELJ)
            val ects = data.getStringExtra(AddPrvaGodinaKolegiji.EXTRA_ECTS)
            val prvaGodinaKolegiji = PrvaGodinaKolegiji(naziv.toString(), nositelj.toString(), ects.toString())
            prvaGodinaViewModel?.update(prvaGodinaKolegiji)*/
            Toast.makeText(this, "Kolegij je ažuriran", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Kolegiji nije spremljen", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val uloga = intent.getStringExtra("Uloga")
        return if ("Admin" == uloga) {
            val menuInflater = menuInflater
            menuInflater.inflate(R.menu.delete_prvi_kolegij_menu, menu)
            true
        } else {
            false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val uloga = intent.getStringExtra("Uloga")
        return if ("Admin" == uloga) {
            when (item.getItemId()) {
                R.id.delete_all_prvi_kolegiji -> {
                    //prvaGodinaViewModel.deleteAll()
                    Toast.makeText(this, "Svi zadaci obrisani", Toast.LENGTH_SHORT).show()
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
