package com.example.barrycards

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel



class PrvaGodinaViewModel(
    application: Application,
) : ViewModel() {

    private var prvaGodinaRepozitorij: PrvaGodinaRepozitorij
    private var sviKolegiji: LiveData<List<PrvaGodinaKolegiji>>
    private var prvaGodinaBaza: PrvaGodinaBaza


    fun insert(prvaGodinaKolegiji: PrvaGodinaKolegiji?) {
        prvaGodinaRepozitorij.insert(prvaGodinaKolegiji)
    }

    fun update(prvaGodinaKolegiji: PrvaGodinaKolegiji?){
        prvaGodinaRepozitorij.update(prvaGodinaKolegiji)
    }

    fun delete(prvaGodinaKolegiji: PrvaGodinaKolegiji?){
        prvaGodinaRepozitorij.delete(prvaGodinaKolegiji)
    }

    fun deleteAll(){
        prvaGodinaRepozitorij.deleteAll()
    }


    fun getSviPrviKolegiji(): LiveData<List<PrvaGodinaKolegiji>> {
        return sviKolegiji
    }

    init {
        val baza: KreiranjeBaze = KreiranjeBaze.getInstance(application)
        prvaGodinaBaza = baza.prvaGodinaBaza()!!
        prvaGodinaRepozitorij = PrvaGodinaRepozitorijOR(application, prvaGodinaBaza)
        sviKolegiji = prvaGodinaRepozitorij.prvaGodinaList
    }
}


