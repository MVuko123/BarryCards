package com.example.barrycards.drugaGodina

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DrugaGodinaKolegiji")
class DrugaGodinaKolegiji(
    @field:ColumnInfo(name = "Naziv") var naziv: String,
    @field:ColumnInfo(name = "Nositelj") var nositelj: String,
    @field:ColumnInfo(name = "ECTS") var ects: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}