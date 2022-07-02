package com.example.barrycards.programiranje1Pitanja

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface Programiranje1Baza {
    @get:Query("Select * from Programiranje1")
    val programiranje1List: LiveData<List<Programiranje1Pitanja>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProgramiranje1(programiranje1Pitanja: Programiranje1Pitanja?)

    @Update
    fun updateProgramiranje1(programiranje1Pitanja: Programiranje1Pitanja?)

    @Delete
    fun deleteProgramiranje1(programiranje1Pitanja: Programiranje1Pitanja?)

    @Query("DELETE FROM Programiranje1")
    fun deleteAllProgramiranje1()
}