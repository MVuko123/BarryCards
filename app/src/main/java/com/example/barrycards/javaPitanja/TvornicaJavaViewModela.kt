package com.example.barrycards.javaPitanja

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.barrycards.programiranje1Pitanja.Programiranje1ViewModel

class TvornicaJavaViewModela(val app:Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JavaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JavaViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}