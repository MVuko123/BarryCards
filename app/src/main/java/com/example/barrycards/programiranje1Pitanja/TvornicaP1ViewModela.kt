package com.example.barrycards.programiranje1Pitanja

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.barrycards.multiMedijskaTehnikaPitanja.MultiMedijskaViewModel

class TvornicaP1ViewModela (val app:Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Programiranje1ViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return Programiranje1ViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}