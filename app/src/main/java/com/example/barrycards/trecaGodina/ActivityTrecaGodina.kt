package com.example.barrycards.trecaGodina

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
import com.example.barrycards.R
import com.example.barrycards.prvaGodina.ActivityPrvaGodina
import com.example.barrycards.prvaGodina.PrvaGodinaAdapter
import com.example.barrycards.prvaGodina.PrvaGodinaKolegiji
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActivityTrecaGodina : AppCompatActivity() {
    private var trecaGodinaViewModel: TrecaGodinaViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treca_godina)

        val buttonDodajKolegiji = findViewById<FloatingActionButton>(R.id.button_add_kolegiji)


        //val uloga = intent.getStringExtra("Uloga")

        //if ("Admin" == uloga || "Superuser" == uloga) {
        buttonDodajKolegiji.visibility = View.VISIBLE
        buttonDodajKolegiji.setOnClickListener {
            val intent = Intent(this@ActivityTrecaGodina, AddEditKolegiji::class.java)
            startActivityForResult(intent, ADD_TRECI_KOLEGIJI_REQUEST)
        }
        /*} else {
            buttonDodajZadatak.visibility = View.GONE
        }*/



        val recyclerViewTreciKolegiji = findViewById<RecyclerView>(R.id.recycler_view_treca_godina)
        recyclerViewTreciKolegiji.layoutManager = LinearLayoutManager(this)
        recyclerViewTreciKolegiji.setHasFixedSize(true)

        val adapterTrecaGodina = TrecaGodinaAdapter()
        recyclerViewTreciKolegiji.adapter = adapterTrecaGodina

        trecaGodinaViewModel = ViewModelProvider(this, TvornicaTrecegViewModela(application))[TrecaGodinaViewModel::class.java]
        trecaGodinaViewModel!!.getSviTreciKolegiji().observe(this
        ) { treciKolegiji -> adapterTrecaGodina.setTreciKolegijiList(treciKolegiji as List<TrecaGodinaKolegiji>) }


        // if ("Admin" == uloga) {
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
                trecaGodinaViewModel?.delete(adapterTrecaGodina.getTreciKolegijiAt(viewHolder.adapterPosition))
                Toast.makeText(this@ActivityTrecaGodina, "Kolegiji obrisan", Toast.LENGTH_SHORT)
                    .show()
            }
        }).attachToRecyclerView(recyclerViewTreciKolegiji)

        adapterTrecaGodina.setOnItemClickListener(object :
        TrecaGodinaAdapter.OnItemClickListenerTreciKolegiji{
            override fun onItemClickTreciKolegiji(trecaGodinaKolegiji: TrecaGodinaKolegiji) {
                Log.d(TAG, "onItemClickTreciKolegiji: treciKolkegiji: $trecaGodinaKolegiji")
                val intent = Intent(this@ActivityTrecaGodina, AddEditKolegiji::class.java)
                intent.putExtra(AddEditKolegiji.EXTRA_ID_PRVI_KOLEGIJI, trecaGodinaKolegiji?.id)
                intent.putExtra(AddEditKolegiji.EXTRA_NAZIV, trecaGodinaKolegiji?.naziv)
                intent.putExtra(AddEditKolegiji.EXTRA_NOSITELJ, trecaGodinaKolegiji?.nositelj)
                intent.putExtra(AddEditKolegiji.EXTRA_ECTS, trecaGodinaKolegiji?.ects)
                startActivityForResult(intent, ActivityTrecaGodina.EDIT_TRECI_KOLEGIJI_REQUEST)
            }
        })
        //}
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_TRECI_KOLEGIJI_REQUEST && resultCode == RESULT_OK) {
            val naziv = data!!.getStringExtra(AddEditKolegiji.EXTRA_NAZIV)
            val nositelj = data.getStringExtra(AddEditKolegiji.EXTRA_NOSITELJ)
            val ects = data.getStringExtra(AddEditKolegiji.EXTRA_ECTS)
            val trecaGodinaKolegiji = TrecaGodinaKolegiji(naziv.toString(), nositelj.toString(), "ECTS:" +ects.toString())
            trecaGodinaViewModel?.insert(trecaGodinaKolegiji)
            Toast.makeText(this, "Kolegiji spremljen", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_TRECI_KOLEGIJI_REQUEST && resultCode == RESULT_OK) {
            val id = data!!.getIntExtra(AddEditKolegiji.EXTRA_ID_PRVI_KOLEGIJI, -1)
            if (id == -1) {
                Toast.makeText(this, "Kolegij ne može biti ažuriran", Toast.LENGTH_SHORT).show()
                return
            }
            val naziv = data.getStringExtra(AddEditKolegiji.EXTRA_NAZIV)
            val nositelj = data.getStringExtra(AddEditKolegiji.EXTRA_NOSITELJ)
            val ects = data.getStringExtra(AddEditKolegiji.EXTRA_ECTS)

            val trecaGodinaKolegiji = TrecaGodinaKolegiji(naziv.toString(), nositelj.toString(), ects.toString())
            trecaGodinaViewModel?.update(trecaGodinaKolegiji)
            Toast.makeText(this, "Kolegij je ažuriran", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Kolegiji nije spremljen", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //val uloga = intent.getStringExtra("Uloga")
        //return if ("Admin" == uloga) {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.delete_all_kolegij_menu, menu)
        return true
        //} else {
        //    false
        //}
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //val uloga = intent.getStringExtra("Uloga")
        //return if ("Admin" == uloga) {
        return when (item.itemId) {
            R.id.delete_all_kolegiji -> {
                trecaGodinaViewModel?.deleteAll()
                Toast.makeText(this, "Svi kolegiji obrisani", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        //} else {
        //    false
        //}
    }

    companion object {
        const val ADD_TRECI_KOLEGIJI_REQUEST = 1
        const val EDIT_TRECI_KOLEGIJI_REQUEST = 2
        private const val TAG = "ActivityTrecaGodina"
    }
    }
