package com.example.barrycards.drugaGodina

import android.app.Application
import androidx.lifecycle.LiveData

class DrugaGodinaRepozitorijOR(application: Application? ,
                                drugaGodinaBaza: DrugaGodinaBaza
) : DrugaGodinaRepozitorij(application, drugaGodinaBaza)
{
    override val drugaGodinaList: LiveData<List<DrugaGodinaKolegiji>>
        get() {
            return this.getDrugaGodinaList
        }
}