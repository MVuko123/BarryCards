package com.example.barrycards

import androidx.lifecycle.LiveData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface PrvaGodinaBaza {
    @get:Query("Select * from PrvaGodinaKolegiji")
    val prvaGodinaList: LiveData<List<PrvaGodinaKolegiji>>

    @Insert
    fun insertPrvaGodina(prvaGodinaKolegiji: PrvaGodinaKolegiji?)
}
