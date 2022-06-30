package com.example.barrycards.programiranje1Pitanja

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.barrycards.multiMedijskaTehnikaPitanja.MultiMedijskaPitanja

@Dao
interface Programiranje1Baza {
    @get:Query("Select * from Programiranje1")
    val programiranje1List: LiveData<List<Programiranje1Pitanja>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProgramiranje1(multiMedija: Programiranje1Pitanja?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateProgramiranje1(multiMedija: Programiranje1Pitanja?)

    @Delete
    fun deleteProgramiranje1(multiMedija: Programiranje1Pitanja?)

    @Query("DELETE FROM Multimedija")
    fun deleteAllProgramiranje1()
}