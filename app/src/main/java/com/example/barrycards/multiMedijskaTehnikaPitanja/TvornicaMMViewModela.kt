package com.example.barrycards.multiMedijskaTehnikaPitanja

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TvornicaMMViewModela(val app:Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MultiMedijskaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MultiMedijskaViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }

}