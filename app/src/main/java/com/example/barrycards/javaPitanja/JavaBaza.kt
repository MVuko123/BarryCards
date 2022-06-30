package com.example.barrycards.javaPitanja

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.barrycards.programiranje1Pitanja.Programiranje1Pitanja

@Dao
interface JavaBaza {

    @get:Query("Select * from Java")
    val javaList: LiveData<List<JavaPitanja>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertJava(javaPitanja: JavaPitanja?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateJava(javaPitanja: JavaPitanja?)

    @Delete
    fun deleteJava(javaPitanja: JavaPitanja?)

    @Query("DELETE FROM Java")
    fun deleteAllJava()
}