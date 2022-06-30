package com.example.barrycards.javaPitanja

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Java")
class JavaPitanja(
    @field:ColumnInfo(name = "Pitanje") var pitanje: String,
    @field:ColumnInfo(name = "Odgovor") var odgovor: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}