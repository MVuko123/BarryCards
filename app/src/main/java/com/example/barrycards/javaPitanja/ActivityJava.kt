package com.example.barrycards.javaPitanja

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
import com.example.barrycards.programiranje1Pitanja.Programiranje1Adapter
import com.example.barrycards.programiranje1Pitanja.Programiranje1Pitanja
import com.example.barrycards.programiranje1Pitanja.Programiranje1ViewModel
import com.example.barrycards.programiranje1Pitanja.TvornicaP1ViewModela
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActivityJava : AppCompatActivity() {
    private var javaViewModel: JavaViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_java)

        val uloga = intent.getStringExtra("Uloga")

        val buttonDodajPitanje = findViewById<FloatingActionButton>(R.id.button_add_pitanje)

        if ("Admin" == uloga ) {
            buttonDodajPitanje.visibility = View.VISIBLE
            buttonDodajPitanje.setOnClickListener {
                val intent = Intent(this@ActivityJava, AddEditPitanja::class.java)
                startActivityForResult(intent, ADD_MULTI_PITANJA_REQUEST)
            }
        } else {
            buttonDodajPitanje.visibility = View.GONE
        }


        val recyclerViewJava = findViewById<RecyclerView>(R.id.recycler_view_java)
        recyclerViewJava.layoutManager = LinearLayoutManager(this)
        recyclerViewJava.setHasFixedSize(true)

        val adapterJava = JavaAdapter()
        recyclerViewJava.adapter = adapterJava



        javaViewModel = ViewModelProvider(this, TvornicaJavaViewModela(application))[JavaViewModel::class.java]
        javaViewModel!!.getSvaJavaPitanja().observe(this
        ) { javaPitanja -> adapterJava.setJavaList(javaPitanja  as List<JavaPitanja>) }


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
                    javaViewModel?.delete(adapterJava.getJavaAt(viewHolder.adapterPosition))
                    Toast.makeText(this@ActivityJava, "Pitanje obrisano", Toast.LENGTH_SHORT)
                        .show()
                }
            }).attachToRecyclerView(recyclerViewJava)


            adapterJava.setOnItemClickListener(object :
                JavaAdapter.OnItemClickListenerJavaPitanja{
                override fun onItemClickJava(javaPitanja: JavaPitanja) {
                    Log.d(TAG, "onItemClickJava: javaPitanja: $javaPitanja")
                    val intent = Intent(this@ActivityJava, AddEditPitanja::class.java)
                    intent.putExtra(AddEditPitanja.EXTRA_ID_PRVA_PITANJA, javaPitanja.id)
                    intent.putExtra(AddEditPitanja.EXTRA_PITANJE, javaPitanja.pitanje)
                    intent.putExtra(AddEditPitanja.EXTRA_ODGOVOR, javaPitanja.odgovor)
                    startActivityForResult(intent, EDIT_MULTI_PITANJA_REQUEST)
                }
            })
        }


        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val actionbar = supportActionBar
        actionbar!!.title="Java Pitanja"
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
            val javaPitanja = JavaPitanja(pitanje.toString(), odgovor.toString())
            javaViewModel?.insert(javaPitanja)
            Toast.makeText(this, "Pitanje spremljeno", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_MULTI_PITANJA_REQUEST && resultCode == RESULT_OK) {
            val id = data!!.getIntExtra(AddEditPitanja.EXTRA_ID_PRVA_PITANJA, -1)
            if (id == -1) {
                Toast.makeText(this, "Pitanje ne može biti ažurirano", Toast.LENGTH_SHORT).show()
                return
            }
            val pitanje = data.getStringExtra(AddEditPitanja.EXTRA_PITANJE)
            val odgovor = data.getStringExtra(AddEditPitanja.EXTRA_ODGOVOR)

            val javaPitanja = JavaPitanja(pitanje.toString(), odgovor.toString())
            javaViewModel?.update(javaPitanja)
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
                    javaViewModel?.deleteAll()
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
        private const val TAG = "ActivityJava"
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
