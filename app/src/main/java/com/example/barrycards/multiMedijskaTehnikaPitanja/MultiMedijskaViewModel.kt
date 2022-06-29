package com.example.barrycards.multiMedijskaTehnikaPitanja

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.barrycards.KreiranjeBaze

class MultiMedijskaViewModel (
    application: Application
) : ViewModel(){
    private var multiMedijskaRepozitorij: MultiMedijskaRepozitorij
    private var svaMMPitanja: LiveData<List<MultiMedijskaPitanja>>
    private var multiMedijskaBaza: MultiMedijskaBaza


    fun insert(multiMedijskaPitanja: MultiMedijskaPitanja?) {
        multiMedijskaRepozitorij.insert(multiMedijskaPitanja)
    }

    fun update(multiMedijskaPitanja: MultiMedijskaPitanja?){
        multiMedijskaRepozitorij.update(multiMedijskaPitanja)
    }

    fun delete(multiMedijskaPitanja: MultiMedijskaPitanja?){
        multiMedijskaRepozitorij.delete(multiMedijskaPitanja)
    }

    fun deleteAll(){
        multiMedijskaRepozitorij.deleteAll()
    }


    fun getSvaMMPitanja(): LiveData<List<MultiMedijskaPitanja>> {
        return svaMMPitanja
    }

    init {
        val baza: KreiranjeBaze = KreiranjeBaze.getInstance(application)
        multiMedijskaBaza = baza.multiMedijskaBaza()!!
        multiMedijskaRepozitorij = MultiMedijskaRepozitorijOR(application, multiMedijskaBaza)
        svaMMPitanja = multiMedijskaRepozitorij.multiMedijaList
    }
}