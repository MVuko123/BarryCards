package com.example.barrycards

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.text.DateFormat
import androidx.annotation.NonNull






@Database(
    entities = [PrvaGodinaKolegiji::class],
    exportSchema = false,
    version = 1
)

abstract class KreiranjeBaze : RoomDatabase() {
    abstract fun prvaGodinaBaza(): PrvaGodinaBaza?
    private class PopuniDbAsycTask(db: KreiranjeBaze) :
        AsyncTask<Void?, Void?, Void?>() {
        private val prvaGodinaBaza: PrvaGodinaBaza = db.prvaGodinaBaza()!!
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
            return null
        }

    }

    companion object {
        private const val DB_NAME = "Tvrtka"
        @Volatile
        private var instance: KreiranjeBaze? = null
        @Synchronized
        fun getInstance(context: Application?): KreiranjeBaze {
            if (KreiranjeBaze.Companion.instance == null) {
                if (context != null) {
                    KreiranjeBaze.Companion.instance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        KreiranjeBaze::class.java, KreiranjeBaze.Companion.DB_NAME
                    ).fallbackToDestructiveMigration().addCallback(KreiranjeBaze.Companion.roomCallback)
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
