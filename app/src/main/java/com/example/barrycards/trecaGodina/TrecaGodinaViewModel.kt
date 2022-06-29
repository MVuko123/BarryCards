package com.example.barrycards.trecaGodina

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.barrycards.KreiranjeBaze


class TrecaGodinaViewModel(


    application: Application
) : ViewModel() {
    private var trecaGodinaRepozitorij: TrecaGodinaRepozitorij
    private var sviKolegiji3: LiveData<List<TrecaGodinaKolegiji>>
    private var trecaGodinaBaza: TrecaGodinaBaza


    fun insert(trecaGodinaKolegiji: TrecaGodinaKolegiji?) {
        trecaGodinaRepozitorij.insert(trecaGodinaKolegiji)
    }

    fun update(trecaGodinaKolegiji: TrecaGodinaKolegiji?){
        trecaGodinaRepozitorij.update(trecaGodinaKolegiji)
    }

    fun delete(trecaGodinaKolegiji: TrecaGodinaKolegiji?){
        trecaGodinaRepozitorij.delete(trecaGodinaKolegiji)
    }

    fun deleteAll(){
        trecaGodinaRepozitorij.deleteAll()
    }


    fun getSviTreciKolegiji(): LiveData<List<TrecaGodinaKolegiji>> {
        return sviKolegiji3
    }

    init {
        val baza: KreiranjeBaze = KreiranjeBaze.getInstance(application)
        trecaGodinaBaza = baza.trecaGodinaBaza()!!
        trecaGodinaRepozitorij = TrecaGodinaRepozitorijOR(application, trecaGodinaBaza)
        sviKolegiji3 = trecaGodinaRepozitorij.trecaGodinaList
    }
}