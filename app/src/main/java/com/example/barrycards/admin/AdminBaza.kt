package com.example.barrycards.admin

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.barrycards.admin.Admin

@Dao
interface AdminBaza {
    @get:Query("Select * from Admin")
    val adminList: LiveData<List<Admin>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAdmin(admin: Admin?)

    @Query("SELECT * from admin where email=(:email) and lozinka=(:lozinka)")
    fun prijava(email: String?, lozinka: String?): Admin?

}