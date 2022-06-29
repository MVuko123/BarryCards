package com.example.barrycards

import android.app.Application
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.barrycards.drugaGodina.DrugaGodinaBaza
import com.example.barrycards.drugaGodina.DrugaGodinaKolegiji
import com.example.barrycards.prvaGodina.PrvaGodinaBaza
import com.example.barrycards.prvaGodina.PrvaGodinaKolegiji


@Database(
    entities = [PrvaGodinaKolegiji::class, DrugaGodinaKolegiji::class],
    exportSchema = false,
    version = 1
)

abstract class KreiranjeBaze : RoomDatabase() {
    abstract fun prvaGodinaBaza(): PrvaGodinaBaza?
    abstract fun drugaGodinaBaza(): DrugaGodinaBaza?
    private class PopuniDbAsycTask(db: KreiranjeBaze) :
        AsyncTask<Void?, Void?, Void?>() {
        private val prvaGodinaBaza: PrvaGodinaBaza = db.prvaGodinaBaza()!!
        private val drugaGodinaBaza: DrugaGodinaBaza = db.drugaGodinaBaza()!!
        override fun doInBackground(vararg params: Void?): Void? {
             prvaGodinaBaza.insertPrvaGodina(
                PrvaGodinaKolegiji(
                    "Programiranje 1",
                    "Robert Šojo",
                    "ECTS:7",
                )
            )
            prvaGodinaBaza.insertPrvaGodina(
                PrvaGodinaKolegiji(
                    "Programiranje 2",
                    "Krešimir Nenadić",
                    "ECTS:8",
                )
            )
            prvaGodinaBaza.insertPrvaGodina(
                PrvaGodinaKolegiji(
                    "Matematika za računarstvo 1",
                    "Ivan Hrehorović",
                    "ECTS:5",
                )
            )
            prvaGodinaBaza.insertPrvaGodina(
                PrvaGodinaKolegiji(
                    "Fizika",
                    "Željka Mioković",
                    "ECTS:5",
                )
            )

            drugaGodinaBaza.insertDrugaGodina(
                DrugaGodinaKolegiji(
                    "Programiranje u Javi" ,
                    "Tomislav Galba" ,
                    "6,5"
                )
            )

            drugaGodinaBaza.insertDrugaGodina(
                DrugaGodinaKolegiji(
                    "Uvod u diskretnu matematiku" ,
                    "Tomislav Rudec" ,
                    "5"
                )
            )

            drugaGodinaBaza.insertDrugaGodina(
                DrugaGodinaKolegiji(
                    "Informacijski sustavi i računalne mreže" ,
                    "Anđelko Lišnjić" ,
                    "7"
                )
            )

            drugaGodinaBaza.insertDrugaGodina(
                DrugaGodinaKolegiji(
                    "Programiranje u Javi" ,
                    "Tomislav Galba" ,
                    "6,5"
                )
            )
            return null
        }

    }

    companion object {
        private const val DB_NAME = "Tvrtka"
        @Volatile
        private var instance: KreiranjeBaze? = null
        @Synchronized
        fun getInstance(context: Application?): KreiranjeBaze {
            if (instance == null) {
                if (context != null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        KreiranjeBaze::class.java, DB_NAME
                    ).fallbackToDestructiveMigration().addCallback(roomCallback)
                        .build()
                }
            }
            return instance!!
        }

        private val roomCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopuniDbAsycTask(instance!!).execute()
            }
        }
    }
}
