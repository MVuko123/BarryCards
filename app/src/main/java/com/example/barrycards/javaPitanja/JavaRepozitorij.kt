package com.example.barrycards.javaPitanja

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.barrycards.KreiranjeBaze
import com.example.barrycards.programiranje1Pitanja.Programiranje1Baza
import com.example.barrycards.programiranje1Pitanja.Programiranje1Pitanja

abstract class JavaRepozitorij(
    application: Application,
    private var javaBaza: JavaBaza
) {
    var getJavaList: LiveData<List<JavaPitanja>>
        get() {
            return field
        }
    abstract val javaList: LiveData<List<JavaPitanja>>



    fun insert(javaPitanja: JavaPitanja?) {
        InsertJavaAsyncTask(javaBaza).execute(javaPitanja)
    }

    fun update(javaPitanja: JavaPitanja?){
        UpdateJavaAyncTask(javaBaza).execute(javaPitanja)
    }

    fun delete(javaPitanja: JavaPitanja?){
        DeleteJavaAyncTask(javaBaza).execute(javaPitanja)
    }

    fun deleteAll() {
        DeleteAllJavaAyncTask(javaBaza).execute()
    }

    private class InsertJavaAsyncTask(javaBaza: JavaBaza) :
        AsyncTask<JavaPitanja?, Void?, Void?>() {
        private val javaBaza: JavaBaza = javaBaza

        override fun doInBackground(vararg params: JavaPitanja?): Void? {
            javaBaza.insertJava(params[0])
            return null
        }
    }

    private class UpdateJavaAyncTask(javaBaza: JavaBaza) :
        AsyncTask<JavaPitanja?, Void?, Void?>() {
        private val javaBaza: JavaBaza = javaBaza

        override fun doInBackground(vararg params: JavaPitanja?): Void? {
            javaBaza.updateJava(params[0])
            return null
        }
    }

    private class DeleteJavaAyncTask(javaBaza: JavaBaza) :
        AsyncTask<JavaPitanja?, Void?, Void?>() {
        private val javaBaza: JavaBaza = javaBaza

        override fun doInBackground(vararg params: JavaPitanja?): Void? {
            javaBaza.deleteJava(params[0])
            return null
        }
    }

    private class DeleteAllJavaAyncTask(javaBaza: JavaBaza) :
        AsyncTask<JavaPitanja?, Void?, Void?>() {
        private val javaBaza: JavaBaza = javaBaza

        override fun doInBackground(vararg params: JavaPitanja?): Void? {
            javaBaza.deleteAllJava()
            return null
        }
    }


    init {
        val baza: KreiranjeBaze = KreiranjeBaze.getInstance(application)
        javaBaza = baza.javaBaza()!!
        getJavaList = javaBaza.javaList
    }
}