package com.example.barrycards

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class PrvaGodinaViewModel(
    application: Application?,
    private var prvaGodinaRepozitorij: PrvaGodinaRepozitorij,
    private var sviKolegiji: LiveData<List<PrvaGodinaKolegiji>>,
    private var prvaGodinaBaza: PrvaGodinaBaza
) : AndroidViewModel(application!!) {

    fun insert(kolegiji: PrvaGodinaKolegiji?) {
        prvaGodinaRepozitorij.insert(kolegiji)
    }


    fun getSviPrviKolegiji(): LiveData<List<PrvaGodinaKolegiji>> {
        return sviKolegiji
    }

    init {
        prvaGodinaRepozitorij = PrvaGodinaRepozitorijOR(application, prvaGodinaBaza)
        sviKolegiji = prvaGodinaRepozitorij.prvaGodinaList
    }
}


