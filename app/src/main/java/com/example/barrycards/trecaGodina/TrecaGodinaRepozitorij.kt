package com.example.barrycards.trecaGodina

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.barrycards.KreiranjeBaze
import com.example.barrycards.prvaGodina.PrvaGodinaBaza
import com.example.barrycards.prvaGodina.PrvaGodinaKolegiji

abstract class TrecaGodinaRepozitorij(
    application: Application?,
    private var trecaGodinaBaza: TrecaGodinaBaza
) {
    var getTrecaGodinaList: LiveData<List<TrecaGodinaKolegiji>>
        get() {
            return field
        }
    abstract val trecaGodinaList: LiveData<List<TrecaGodinaKolegiji>>



    fun insert(trecaGodinaKolegiji: TrecaGodinaKolegiji?) {
        InsertTrecaGodinaAsyncTask(trecaGodinaBaza).execute(trecaGodinaKolegiji)
    }

    fun update(trecaGodinaKolegiji: TrecaGodinaKolegiji?){
        UpdateTrecaGodinaAyncTask(trecaGodinaBaza).execute(trecaGodinaKolegiji)
    }

    fun delete(trecaGodinaKolegiji: TrecaGodinaKolegiji?){
        DeleteTrecaGodinaAyncTask(trecaGodinaBaza).execute(trecaGodinaKolegiji)
    }

    fun deleteAll() {
        DeleteAllTrecaGodinaAyncTask(trecaGodinaBaza).execute()
    }

    private class InsertTrecaGodinaAsyncTask(trecaGodinaBaza: TrecaGodinaBaza) :
        AsyncTask<TrecaGodinaKolegiji?, Void?, Void?>() {
        private val trecaGodinaBaza: TrecaGodinaBaza = trecaGodinaBaza

        override fun doInBackground(vararg params: TrecaGodinaKolegiji?): Void? {
            trecaGodinaBaza.insertTrecaGodina(params[0])
            return null
        }
    }

    private class UpdateTrecaGodinaAyncTask(trecaGodinaBaza: TrecaGodinaBaza) :
        AsyncTask<TrecaGodinaKolegiji?, Void?, Void?>() {
        private val trecaGodinaBaza: TrecaGodinaBaza = trecaGodinaBaza

        override fun doInBackground(vararg params: TrecaGodinaKolegiji?): Void? {
            trecaGodinaBaza.updateTrecaGodina(params[0])
            return null
        }
    }

    private class DeleteTrecaGodinaAyncTask(trecaGodinaBaza: TrecaGodinaBaza) :
        AsyncTask<TrecaGodinaKolegiji?, Void?, Void?>() {
        private val trecaGodinaBaza: TrecaGodinaBaza = trecaGodinaBaza

        override fun doInBackground(vararg params: TrecaGodinaKolegiji?): Void? {
            trecaGodinaBaza.deleteTrecaGodina(params[0])
            return null
        }
    }

    private class DeleteAllTrecaGodinaAyncTask(trecaGodinaBaza: TrecaGodinaBaza) :
        AsyncTask<TrecaGodinaKolegiji?, Void?, Void?>() {
        private val trecaGodinaBaza: TrecaGodinaBaza = trecaGodinaBaza

        override fun doInBackground(vararg params: TrecaGodinaKolegiji?): Void? {
            trecaGodinaBaza.deleteAllTrecaGodinaKolegiji()
            return null
        }
    }


    init {
        val baza: KreiranjeBaze = KreiranjeBaze.getInstance(application)
        trecaGodinaBaza = baza.trecaGodinaBaza()!!
        getTrecaGodinaList = trecaGodinaBaza.trecaGodinaList
    }
}