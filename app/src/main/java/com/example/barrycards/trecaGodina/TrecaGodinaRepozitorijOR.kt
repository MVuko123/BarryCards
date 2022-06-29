package com.example.barrycards.trecaGodina

import android.app.Application
import androidx.lifecycle.LiveData

class TrecaGodinaRepozitorijOR(
    application: Application?,
    trecaGodinaBaza: TrecaGodinaBaza
) : TrecaGodinaRepozitorij(application, trecaGodinaBaza) {
    override val trecaGodinaList: LiveData<List<TrecaGodinaKolegiji>>
        get() {
            return this.getTrecaGodinaList
        }
}