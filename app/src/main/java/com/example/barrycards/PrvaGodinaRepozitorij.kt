package com.example.barrycards

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData


abstract class PrvaGodinaRepozitorij(
    application: Application?,
    private var prvaGodinaBaza: PrvaGodinaBaza
) {
    var getPrvaGodinaList: LiveData<List<PrvaGodinaKolegiji>>
        get() {
            return field
        }
    abstract val prvaGodinaList: LiveData<List<PrvaGodinaKolegiji>>



    fun insert(prvaGodinaKolegiji: PrvaGodinaKolegiji?) {
        InsertPrvaGodinaAsyncTask(prvaGodinaBaza).execute(prvaGodinaKolegiji)
    }

    private class InsertPrvaGodinaAsyncTask(prvaGodinaBaza: PrvaGodinaBaza) :
        AsyncTask<PrvaGodinaKolegiji?, Void?, Void?>() {
        private val prvaGodinaBaza: PrvaGodinaBaza

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


