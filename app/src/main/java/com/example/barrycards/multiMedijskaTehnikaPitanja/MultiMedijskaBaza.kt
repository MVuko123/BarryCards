package com.example.barrycards.multiMedijskaTehnikaPitanja

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MultiMedijskaBaza {
    @get:Query("Select * from Multimedija")
    val multiMedijaList: LiveData<List<MultiMedijskaPitanja>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMultiMedija(multiMedija: MultiMedijskaPitanja?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMultiMedija(multiMedija: MultiMedijskaPitanja?)

    @Delete
    fun deleteMultiMedija(multiMedija: MultiMedijskaPitanja?)

    @Query("DELETE FROM Multimedija")
    fun deleteAllMultiMedija()
}