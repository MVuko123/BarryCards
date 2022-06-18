package com.example.barrycards

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData


abstract class PrvaGodinaRepozitorij(
    application: Application?,
    private var prvaGodinaBaza: PrvaGodinaBaza
) {
    private var getPrvaGodinaList: LiveData<List<PrvaGodinaKolegiji>>
        get() {
            return getPrvaGodinaList
        }
    abstract val prvaGodinaList: LiveData<List<PrvaGodinaKolegiji>>

    fun insert(prvaGodinaKolegiji: PrvaGodinaKolegiji?) {}
    private abstract class InsertPrvaGodinaAsyncTask private constructor(prvaGodinaBaza: PrvaGodinaBaza) :
        AsyncTask<PrvaGodinaKolegiji?, Void?, Void?>() {
        private val prvaGodinaBaza: PrvaGodinaBaza = TODO()

        init {
            this.prvaGodinaBaza = prvaGodinaBaza
        }

        override fun doInBackground(vararg params: PrvaGodinaKolegiji?): Void? {
            prvaGodinaBaza.insertPrvaGodina(params[0])
            return null
        }
    }

    init {
        val baza: KreiranjeBaze = KreiranjeBaze.getInstance(application)
        prvaGodinaBaza = baza.prvaGodinaBaza()!!
        getPrvaGodinaList = prvaGodinaBaza.prvaGodinaList
    }


}
