package com.example.barrycards.drugaGodina

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TvornicaDrugogViewModela(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DrugaGodinaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DrugaGodinaViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}