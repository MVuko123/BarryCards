package com.example.barrycards.admin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Admin")
class Admin(
    @field:ColumnInfo(name = "Email") var email: String ,
    @field:ColumnInfo(name = "Lozinka") var lozinka: String,
    @field:ColumnInfo(name = "Uloga") var uloga: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}