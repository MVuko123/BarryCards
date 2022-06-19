package com.example.barrycards

import android.app.Application
import androidx.lifecycle.LiveData

class PrvaGodinaRepozitorijOR(application: Application?,
                                       prvaGodinaBaza: PrvaGodinaBaza
) : PrvaGodinaRepozitorij(application, prvaGodinaBaza) {
     override val prvaGodinaList: LiveData<List<PrvaGodinaKolegiji>>
        get() {
            return this.getPrvaGodinaList
        }
}