package com.example.barrycards.multiMedijskaTehnikaPitanja

import android.app.Application
import androidx.lifecycle.LiveData

class MultiMedijskaRepozitorijOR (
    application: Application,
    multiMedijskaBaza: MultiMedijskaBaza,
) : MultiMedijskaRepozitorij(application, multiMedijskaBaza){
    override val multiMedijaList: LiveData<List<MultiMedijskaPitanja>>
    get(){
        return this.getMultiMedijaList
    }

}