package com.example.barrycards.programiranje1Pitanja

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.barrycards.KreiranjeBaze

abstract class Programiranje1Repozitorij(
    application: Application,
    private var programiranje1Baza: Programiranje1Baza
) {
    var getProgramiranje1List: LiveData<List<Programiranje1Pitanja>>
        get() {
            return field
        }
    abstract val programiranje1List: LiveData<List<Programiranje1Pitanja>>



    fun insert(programiranje1Pitanja: Programiranje1Pitanja?) {
        InsertProgramiranje1AsyncTask(programiranje1Baza).execute(programiranje1Pitanja)
    }

    fun update(programiranje1Pitanja: Programiranje1Pitanja?){
        UpdateProgramiranje1AyncTask(programiranje1Baza).execute(programiranje1Pitanja)
    }

    fun delete(programiranje1Pitanja: Programiranje1Pitanja?){
        DeleteProgramiranje1AyncTask(programiranje1Baza).execute(programiranje1Pitanja)
    }

    fun deleteAll() {
        DeleteAllProgramiranje1AyncTask(programiranje1Baza).execute()
    }

    private class InsertProgramiranje1AsyncTask(programiranje1Baza: Programiranje1Baza) :
        AsyncTask<Programiranje1Pitanja?, Void?, Void?>() {
        private val programiranje1Baza: Programiranje1Baza = programiranje1Baza

        override fun doInBackground(vararg params: Programiranje1Pitanja?): Void? {
            programiranje1Baza.insertProgramiranje1(params[0])
            return null
        }
    }

    private class UpdateProgramiranje1AyncTask(programiranje1Baza: Programiranje1Baza) :
        AsyncTask<Programiranje1Pitanja?, Void?, Void?>() {
        private val programiranje1Baza: Programiranje1Baza = programiranje1Baza

        override fun doInBackground(vararg params: Programiranje1Pitanja?): Void? {
            programiranje1Baza.updateProgramiranje1(params[0])
            return null
        }
    }

    private class DeleteProgramiranje1AyncTask(programiranje1Baza: Programiranje1Baza) :
        AsyncTask<Programiranje1Pitanja?, Void?, Void?>() {
        private val programiranje1Baza: Programiranje1Baza = programiranje1Baza

        override fun doInBackground(vararg params: Programiranje1Pitanja?): Void? {
            programiranje1Baza.deleteProgramiranje1(params[0])
            return null
        }
    }

    private class DeleteAllProgramiranje1AyncTask(programiranje1Baza: Programiranje1Baza) :
        AsyncTask<Programiranje1Pitanja?, Void?, Void?>() {
        private val programiranje1Baza: Programiranje1Baza = programiranje1Baza

        override fun doInBackground(vararg params: Programiranje1Pitanja?): Void? {
            programiranje1Baza.deleteAllProgramiranje1()
            return null
        }
    }


    init {
        val baza: KreiranjeBaze = KreiranjeBaze.getInstance(application)
        programiranje1Baza = baza.programiranje1Baza()!!
        getProgramiranje1List = programiranje1Baza.programiranje1List
    }

}