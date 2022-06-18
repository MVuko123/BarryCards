package com.example.barrycards

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class PrvaGodinaViewModel(
    application: Application?
) : AndroidViewModel(application!!) {

    private var prvaGodinaRepozitorij: PrvaGodinaRepozitorij
    private var sviKolegiji: LiveData<List<PrvaGodinaKolegiji>>
    private lateinit var prvaGodinaBaza: PrvaGodinaBaza

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


