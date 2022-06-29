package com.example.barrycards.multiMedijskaTehnikaPitanja

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.barrycards.KreiranjeBaze
import com.example.barrycards.trecaGodina.TrecaGodinaBaza
import com.example.barrycards.trecaGodina.TrecaGodinaKolegiji

abstract class MultiMedijskaRepozitorij (
    application: Application?,
    private var multiMedijskaBaza: MultiMedijskaBaza
    ) {
        var getMultiMedijaList: LiveData<List<MultiMedijskaPitanja>>
        get() {
            return field
        }
        abstract val multiMedijaList: LiveData<List<MultiMedijskaPitanja>>



        fun insert(multiMedijskaPitanja: MultiMedijskaPitanja?) {
            InsertMultiMedijaAsyncTask(multiMedijskaBaza).execute(multiMedijskaPitanja)
        }

        fun update(multiMedijskaPitanja: MultiMedijskaPitanja?){
            UpdateMultiMedijaAyncTask(multiMedijskaBaza).execute(multiMedijskaPitanja)
        }

        fun delete(multiMedijskaPitanja: MultiMedijskaPitanja?){
            DeleteMultiMedijaAyncTask(multiMedijskaBaza).execute(multiMedijskaPitanja)
        }

        fun deleteAll() {
            DeleteAllMultiMedijaAyncTask(multiMedijskaBaza).execute()
        }

        private class InsertMultiMedijaAsyncTask(multiMedijskaBaza: MultiMedijskaBaza) :
            AsyncTask<MultiMedijskaPitanja?, Void?, Void?>() {
            private val multiMedijskaBaza: MultiMedijskaBaza = multiMedijskaBaza

            override fun doInBackground(vararg params: MultiMedijskaPitanja?): Void? {
                multiMedijskaBaza.insertMultiMedija(params[0])
                return null
            }
        }

        private class UpdateMultiMedijaAyncTask(multiMedijskaBaza: MultiMedijskaBaza) :
            AsyncTask<MultiMedijskaPitanja?, Void?, Void?>() {
            private val multiMedijskaBaza: MultiMedijskaBaza = multiMedijskaBaza

            override fun doInBackground(vararg params: MultiMedijskaPitanja?): Void? {
                multiMedijskaBaza.updateMultiMedija(params[0])
                return null
            }
        }

        private class DeleteMultiMedijaAyncTask(multiMedijskaBaza: MultiMedijskaBaza) :
            AsyncTask<MultiMedijskaPitanja?, Void?, Void?>() {
            private val multiMedijskaBaza: MultiMedijskaBaza = multiMedijskaBaza

            override fun doInBackground(vararg params: MultiMedijskaPitanja?): Void? {
                multiMedijskaBaza.deleteMultiMedija(params[0])
                return null
            }
        }

        private class DeleteAllMultiMedijaAyncTask(multiMedijskaBaza: MultiMedijskaBaza) :
            AsyncTask<MultiMedijskaPitanja?, Void?, Void?>() {
            private val multiMedijskaBaza: MultiMedijskaBaza = multiMedijskaBaza

            override fun doInBackground(vararg params: MultiMedijskaPitanja?): Void? {
                multiMedijskaBaza.deleteAllMultiMedija()
                return null
            }
        }


        init {
            val baza: KreiranjeBaze = KreiranjeBaze.getInstance(application)
            multiMedijskaBaza = baza.multiMedijskaBaza()!!
            getMultiMedijaList = multiMedijskaBaza.multiMedijaList
        }
    }