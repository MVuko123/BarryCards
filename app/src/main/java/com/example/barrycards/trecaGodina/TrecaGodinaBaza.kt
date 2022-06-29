package com.example.barrycards.trecaGodina

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.barrycards.drugaGodina.DrugaGodinaKolegiji

@Dao
interface TrecaGodinaBaza {
    @get:Query("Select * from TrecaGodinaKolegiji")
    val trecaGodinaList: LiveData<List<TrecaGodinaKolegiji>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTrecaGodina(trecaGodinaKolegiji: TrecaGodinaKolegiji?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTrecaGodina(trecaGodinaKolegiji: TrecaGodinaKolegiji?)

    @Delete
    fun deleteTrecaGodina(trecaGodinaKolegiji: TrecaGodinaKolegiji?)

    @Query("DELETE FROM TrecaGodinaKolegiji")
    fun deleteAllTrecaGodinaKolegiji()
}