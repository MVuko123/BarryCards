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

    fun update(prvaGodinaKolegiji: PrvaGodinaKolegiji?){
        UpdatePrvaGodinaAyncTask(prvaGodinaBaza).execute(prvaGodinaKolegiji)
    }

    fun delete(prvaGodinaKolegiji: PrvaGodinaKolegiji?){
        DeletePrvaGodinaAyncTask(prvaGodinaBaza).execute(prvaGodinaKolegiji)
    }

    fun deleteAll() {
        DeleteAllPrvaGodinaAyncTask(prvaGodinaBaza).execute()
    }

    private class InsertPrvaGodinaAsyncTask(prvaGodinaBaza: PrvaGodinaBaza) :
        AsyncTask<PrvaGodinaKolegiji?, Void?, Void?>() {
        private val prvaGodinaBaza: PrvaGodinaBaza = prvaGodinaBaza

        override fun doInBackground(vararg params: PrvaGodinaKolegiji?): Void? {
            prvaGodinaBaza.insertPrvaGodina(params[0])
            return null
        }
    }

    private class UpdatePrvaGodinaAyncTask(prvaGodinaBaza: PrvaGodinaBaza) :
        AsyncTask<PrvaGodinaKolegiji?, Void?, Void?>() {
        private val prvaGodinaBaza: PrvaGodinaBaza = prvaGodinaBaza

        override fun doInBackground(vararg params: PrvaGodinaKolegiji?): Void? {
            prvaGodinaBaza.updatePrvaGodina(params[0])
            return null
        }
    }

    private class DeletePrvaGodinaAyncTask(prvaGodinaBaza: PrvaGodinaBaza) :
        AsyncTask<PrvaGodinaKolegiji?, Void?, Void?>() {
        private val prvaGodinaBaza: PrvaGodinaBaza = prvaGodinaBaza

        override fun doInBackground(vararg params: PrvaGodinaKolegiji?): Void? {
            prvaGodinaBaza.deletePrvaGodina(params[0])
            return null
        }
    }

    private class DeleteAllPrvaGodinaAyncTask(prvaGodinaBaza: PrvaGodinaBaza) :
        AsyncTask<PrvaGodinaKolegiji?, Void?, Void?>() {
        private val prvaGodinaBaza: PrvaGodinaBaza = prvaGodinaBaza

        override fun doInBackground(vararg params: PrvaGodinaKolegiji?): Void? {
            prvaGodinaBaza.deleteAllPrvaGodinaKolegiji()
            return null
        }
    }


    init {
        val baza: KreiranjeBaze = KreiranjeBaze.getInstance(application)
        prvaGodinaBaza = baza.prvaGodinaBaza()!!
        getPrvaGodinaList = prvaGodinaBaza.prvaGodinaList
    }



}


