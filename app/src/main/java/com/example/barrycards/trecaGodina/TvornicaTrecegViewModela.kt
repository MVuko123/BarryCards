package com.example.barrycards.trecaGodina

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TvornicaTrecegViewModela(val app:Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrecaGodinaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TrecaGodinaViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}