package com.example.barrycards.programiranje1Pitanja

import android.app.Application
import androidx.lifecycle.LiveData

class Programiranje1RepozitorijOR(
    application: Application,
    programiranje1Baza: Programiranje1Baza,
) : Programiranje1Repozitorij(application, programiranje1Baza) {
    override val programiranje1List: LiveData<List<Programiranje1Pitanja>>
    get(){
        return this.getProgramiranje1ist
    }

}