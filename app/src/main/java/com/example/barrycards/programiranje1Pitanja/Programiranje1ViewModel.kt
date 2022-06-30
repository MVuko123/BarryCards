package com.example.barrycards.programiranje1Pitanja

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.barrycards.KreiranjeBaze
import com.example.barrycards.multiMedijskaTehnikaPitanja.MultiMedijskaBaza
import com.example.barrycards.multiMedijskaTehnikaPitanja.MultiMedijskaPitanja
import com.example.barrycards.multiMedijskaTehnikaPitanja.MultiMedijskaRepozitorij
import com.example.barrycards.multiMedijskaTehnikaPitanja.MultiMedijskaRepozitorijOR

class Programiranje1ViewModel(
    application: Application
) : ViewModel() {
    private var programiranje1Repozitorij: Programiranje1Repozitorij
    private var svaP1Pitanja: LiveData<List<Programiranje1Pitanja>>
    private var programiranje1Baza: Programiranje1Baza


    fun insert(programiranje1Pitanja: Programiranje1Pitanja?) {
        programiranje1Repozitorij.insert(programiranje1Pitanja)
    }

    fun update(programiranje1Pitanja: Programiranje1Pitanja?){
        programiranje1Repozitorij.update(programiranje1Pitanja)
    }

    fun delete(programiranje1Pitanja: Programiranje1Pitanja?){
        programiranje1Repozitorij.delete(programiranje1Pitanja)
    }

    fun deleteAll(){
        programiranje1Repozitorij.deleteAll()
    }


    fun getSvaP1Pitanja(): LiveData<List<Programiranje1Pitanja>> {
        return svaP1Pitanja
    }

    init {
        val baza: KreiranjeBaze = KreiranjeBaze.getInstance(application)
        programiranje1Baza = baza.programiranje1Baza()!!
        programiranje1Repozitorij = Programiranje1RepozitorijOR(application, programiranje1Baza)
        svaP1Pitanja = programiranje1Repozitorij.programiranje1List
    }
}