package com.example.barrycards.javaPitanja

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.barrycards.KreiranjeBaze
import com.example.barrycards.R
import com.example.barrycards.programiranje1Pitanja.Programiranje1Baza
import com.example.barrycards.programiranje1Pitanja.Programiranje1Pitanja
import com.example.barrycards.programiranje1Pitanja.Programiranje1Repozitorij
import com.example.barrycards.programiranje1Pitanja.Programiranje1RepozitorijOR

class JavaViewModel(
    application: Application
): ViewModel(){
    private var javaRepozitorij: JavaRepozitorij
    private var svaJavaPitanja: LiveData<List<JavaPitanja>>
    private var javaBaza: JavaBaza


    fun insert(javaPitanja: JavaPitanja?) {
        javaRepozitorij.insert(javaPitanja)
    }

    fun update(javaPitanja: JavaPitanja?){
        javaRepozitorij.update(javaPitanja)
    }

    fun delete(javaPitanja: JavaPitanja?){
        javaRepozitorij.delete(javaPitanja)
    }

    fun deleteAll(){
        javaRepozitorij.deleteAll()
    }


    fun getSvaJavaPitanja(): LiveData<List<JavaPitanja>> {
        return svaJavaPitanja
    }

    init {
        val baza: KreiranjeBaze = KreiranjeBaze.getInstance(application)
        javaBaza = baza.javaBaza()!!
        javaRepozitorij = JavaRepozitoijOR(application, javaBaza)
        svaJavaPitanja = javaRepozitorij.javaList
    }
}