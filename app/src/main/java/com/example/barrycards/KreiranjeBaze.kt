package com.example.barrycards

import android.app.Application
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.barrycards.admin.Admin
import com.example.barrycards.admin.AdminBaza
import com.example.barrycards.drugaGodina.DrugaGodinaBaza
import com.example.barrycards.drugaGodina.DrugaGodinaKolegiji
import com.example.barrycards.javaPitanja.JavaBaza
import com.example.barrycards.javaPitanja.JavaPitanja
import com.example.barrycards.multiMedijskaTehnikaPitanja.MultiMedijskaBaza
import com.example.barrycards.multiMedijskaTehnikaPitanja.MultiMedijskaPitanja
import com.example.barrycards.programiranje1Pitanja.Programiranje1Baza
import com.example.barrycards.programiranje1Pitanja.Programiranje1Pitanja
import com.example.barrycards.prvaGodina.PrvaGodinaBaza
import com.example.barrycards.prvaGodina.PrvaGodinaKolegiji
import com.example.barrycards.trecaGodina.TrecaGodinaBaza
import com.example.barrycards.trecaGodina.TrecaGodinaKolegiji


@Database(
    entities = [PrvaGodinaKolegiji::class, DrugaGodinaKolegiji::class, TrecaGodinaKolegiji::class, Admin::class, MultiMedijskaPitanja::class, Programiranje1Pitanja::class, JavaPitanja::class],
    exportSchema = false,
    version = 1
)

abstract class KreiranjeBaze : RoomDatabase() {
    abstract fun prvaGodinaBaza(): PrvaGodinaBaza?
    abstract fun drugaGodinaBaza(): DrugaGodinaBaza?
    abstract fun trecaGodinaBaza(): TrecaGodinaBaza?
    abstract fun adminBaza(): AdminBaza?
    abstract fun multiMedijskaBaza(): MultiMedijskaBaza?
    abstract fun programiranje1Baza(): Programiranje1Baza?
    abstract fun javaBaza(): JavaBaza?

    private class PopuniDbAsycTask(db: KreiranjeBaze) :
        AsyncTask<Void?, Void?, Void?>() {
        private val prvaGodinaBaza: PrvaGodinaBaza = db.prvaGodinaBaza()!!
        private val drugaGodinaBaza: DrugaGodinaBaza = db.drugaGodinaBaza()!!
        private val trecaGodinaBaza: TrecaGodinaBaza = db.trecaGodinaBaza()!!
        private val adminBaza: AdminBaza = db.adminBaza()!!
        private val multiMedijskaBaza: MultiMedijskaBaza = db.multiMedijskaBaza()!!
        private val programiranje1Baza: Programiranje1Baza = db.programiranje1Baza()!!
        private val javaBaza: JavaBaza = db.javaBaza()!!
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
                    "ECTS:6,5"
                )
            )

            drugaGodinaBaza.insertDrugaGodina(
                DrugaGodinaKolegiji(
                    "Uvod u diskretnu matematiku" ,
                    "Tomislav Rudec" ,
                    "ECTS:5"
                )
            )

            drugaGodinaBaza.insertDrugaGodina(
                DrugaGodinaKolegiji(
                    "Informacijski sustavi i računalne mreže" ,
                    "Anđelko Lišnjić" ,
                    "ECTS:7"
                )
            )

            drugaGodinaBaza.insertDrugaGodina(
                DrugaGodinaKolegiji(
                    "Digitalna elektornika" ,
                    "Marijan Herceg" ,
                    "ECTS:6"
                )
            )

            trecaGodinaBaza.insertTrecaGodina(
                TrecaGodinaKolegiji(
                    "Dizajn korisničkog sučelja",
                    "Časlav Livada",
                    "ECTS:5"
                )
            )
            trecaGodinaBaza.insertTrecaGodina(
                TrecaGodinaKolegiji(
                    "Razvoj mobilnih aplikacija",
                    "Krešimir Nenadić",
                    "ECTS:5"
                )
            )
            trecaGodinaBaza.insertTrecaGodina(
                TrecaGodinaKolegiji(
                    "Digitalne komunikacije",
                    "Vanja Mandrić",
                    "ECTS:7.5"
                )
            )
            trecaGodinaBaza.insertTrecaGodina(
                TrecaGodinaKolegiji(
                    "Multimedijska tehnika",
                    "Mario Vranješ",
                    "ECTS:5"
                )
            )
            adminBaza.insertAdmin(
                Admin(
                    "vuko.marin52@gmail.com",
                    "A12345678b",
                    "Admin"
                )
            )
            multiMedijskaBaza.insertMultiMedija(
                MultiMedijskaPitanja(
                    "Kaki se zove pokretna slika bez zvuka koja se konstatno vrti?",
                    "GIF",
                )
            )
            multiMedijskaBaza.insertMultiMedija(
                MultiMedijskaPitanja(
                    "Što je hipertekst?",
                    "To je kada u teksu imamo link na drugi tekst.",
                )
            )
            programiranje1Baza.insertProgramiranje1(
                Programiranje1Pitanja(
                    "Tko se smatra ocem teoretičke računarske znanosti i umjetne inteligencije?",
                    "Alan Turing",
                )
            )
            programiranje1Baza.insertProgramiranje1(
                Programiranje1Pitanja(
                    "Koje je ime prvog računala konstruiranog u Americi i što znači?",
                    "ENIAC - Electronic Numerical Integrator And Computer",
                )
            )
            javaBaza.insertJava(
                JavaPitanja(
                    "Što znači JDK i JRE?",
                    "Java Development Kit, Java Runtime Enviroment",
                )
            )
            javaBaza.insertJava(
                JavaPitanja(
                    "Što će se dogoditi ako main() nije static?",
                    "Dobiti ćemo error: \"NoSuchMethodError.\"",
                )
            )
            return null
        }

    }

    companion object {
        private const val DB_NAME = "Fakultet"
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
