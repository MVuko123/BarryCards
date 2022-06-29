package com.example.barrycards.drugaGodina

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DrugaGodinaBaza {
    @get:Query("Select * from DrugaGodinaKolegiji")
    val drugaGodinaList: LiveData<List<DrugaGodinaKolegiji>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDrugaGodina(drugaGodinaKolegiji: DrugaGodinaKolegiji?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateDrugaGodina(drugaGodinaKolegiji: DrugaGodinaKolegiji?)

    @Delete
    fun deleteDrugaGodina(drugaGodinaKolegiji: DrugaGodinaKolegiji?)

    @Query("DELETE FROM DrugaGodinaKolegiji")
    fun deleteAllDrugaGodinaKolegiji()
}