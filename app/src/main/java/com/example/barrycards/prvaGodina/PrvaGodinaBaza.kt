package com.example.barrycards.prvaGodina

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface PrvaGodinaBaza {
    @get:Query("Select * from PrvaGodinaKolegiji")
    val prvaGodinaList: LiveData<List<PrvaGodinaKolegiji>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPrvaGodina(prvaGodinaKolegiji: PrvaGodinaKolegiji?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePrvaGodina(prvaGodinaKolegiji: PrvaGodinaKolegiji?)

    @Delete
    fun deletePrvaGodina(prvaGodinaKolegiji: PrvaGodinaKolegiji?)

    @Query("DELETE FROM PrvaGodinaKolegiji")
    fun deleteAllPrvaGodinaKolegiji()
}
