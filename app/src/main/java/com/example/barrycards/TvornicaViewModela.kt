package com.example.barrycards

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TvornicaViewModela( val app: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PrvaGodinaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PrvaGodinaViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}