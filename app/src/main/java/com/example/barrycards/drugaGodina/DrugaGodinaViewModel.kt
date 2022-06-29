package com.example.barrycards.drugaGodina

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.barrycards.KreiranjeBaze


class DrugaGodinaViewModel (
    application: Application?
) : ViewModel() {

    private var drugaGodinaRepozitorij: DrugaGodinaRepozitorij
    private var sviKolegiji2: LiveData<List<DrugaGodinaKolegiji>>
    private var drugaGodinaBaza: DrugaGodinaBaza


    fun insert(drugaGodinaKolegiji: DrugaGodinaKolegiji?) {
        drugaGodinaRepozitorij.insert(drugaGodinaKolegiji)
    }

    fun update(drugaGodinaKolegiji: DrugaGodinaKolegiji?){
        drugaGodinaRepozitorij.update(drugaGodinaKolegiji)
    }

    fun delete(drugaGodinaKolegiji: DrugaGodinaKolegiji?){
        drugaGodinaRepozitorij.delete(drugaGodinaKolegiji)
    }

    fun deleteAll(){
        drugaGodinaRepozitorij.deleteAll()
    }


    fun getSviDrugiKolegiji(): LiveData<List<DrugaGodinaKolegiji>> {
        return sviKolegiji2
    }

    init {
        val baza: KreiranjeBaze = KreiranjeBaze.getInstance(application)
        drugaGodinaBaza = baza.drugaGodinaBaza()!!
        drugaGodinaRepozitorij = DrugaGodinaRepozitorijOR(application, drugaGodinaBaza)
        sviKolegiji2 = drugaGodinaRepozitorij.drugaGodinaList
    }
}