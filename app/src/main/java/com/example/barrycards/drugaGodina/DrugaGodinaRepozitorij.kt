package com.example.barrycards.drugaGodina

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.barrycards.KreiranjeBaze


abstract class DrugaGodinaRepozitorij(
    application: Application? ,
    private var drugaGodinaBaza: DrugaGodinaBaza
) {
    var getDrugaGodinaList: LiveData<List<DrugaGodinaKolegiji>>
        get() {
            return field
        }
    abstract val drugaGodinaList: LiveData<List<DrugaGodinaKolegiji>>



    fun insert(drugaGodinaKolegiji: DrugaGodinaKolegiji?) {
        InsertDrugaGodinaAsyncTask(drugaGodinaBaza).execute(drugaGodinaKolegiji)
    }

    fun update(drugaGodinaKolegiji: DrugaGodinaKolegiji?){
        UpdateDrugaGodinaAyncTask(drugaGodinaBaza).execute(drugaGodinaKolegiji)
    }

    fun delete(drugaGodinaKolegiji: DrugaGodinaKolegiji?){
        DeleteDrugaGodinaAyncTask(drugaGodinaBaza).execute(drugaGodinaKolegiji)
    }

    fun deleteAll() {
        DeleteAllDrugaGodinaAyncTask(drugaGodinaBaza).execute()
    }

    private class InsertDrugaGodinaAsyncTask(drugaGodinaBaza: DrugaGodinaBaza) :
        AsyncTask<DrugaGodinaKolegiji?, Void?, Void?>() {
        private val drugaGodinaBaza: DrugaGodinaBaza = drugaGodinaBaza

        override fun doInBackground(vararg params: DrugaGodinaKolegiji?): Void? {
            drugaGodinaBaza.insertDrugaGodina(params[0])
            return null
        }
    }

    private class UpdateDrugaGodinaAyncTask(drugaGodinaBaza: DrugaGodinaBaza) :
        AsyncTask<DrugaGodinaKolegiji?, Void?, Void?>() {
        private val drugaGodinaBaza: DrugaGodinaBaza = drugaGodinaBaza

        override fun doInBackground(vararg params: DrugaGodinaKolegiji?): Void? {
            drugaGodinaBaza.updateDrugaGodina(params[0])
            return null
        }
    }

    private class DeleteDrugaGodinaAyncTask(drugaGodinaBaza: DrugaGodinaBaza) :
        AsyncTask<DrugaGodinaKolegiji?, Void?, Void?>() {
        private val drugaGodinaBaza: DrugaGodinaBaza = drugaGodinaBaza

        override fun doInBackground(vararg params: DrugaGodinaKolegiji?): Void? {
            drugaGodinaBaza.deleteDrugaGodina(params[0])
            return null
        }
    }

    private class DeleteAllDrugaGodinaAyncTask(drugaGodinaBaza: DrugaGodinaBaza) :
        AsyncTask<DrugaGodinaKolegiji?, Void?, Void?>() {
        private val prvaGodinaBaza: DrugaGodinaBaza = drugaGodinaBaza

        override fun doInBackground(vararg params: DrugaGodinaKolegiji?): Void? {
            prvaGodinaBaza.deleteAllDrugaGodinaKolegiji()
            return null
        }
    }


    init {
        val baza: KreiranjeBaze = KreiranjeBaze.getInstance(application)
        drugaGodinaBaza = baza.drugaGodinaBaza()!!
        getDrugaGodinaList = drugaGodinaBaza.drugaGodinaList
    }
}