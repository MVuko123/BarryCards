package com.example.barrycards.programiranje1Pitanja

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Programiranje1")
class Programiranje1Pitanja(
    @field:ColumnInfo(name = "Pitanje") var pitanje: String,
    @field:ColumnInfo(name = "Odgovor") var odgovor: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}

