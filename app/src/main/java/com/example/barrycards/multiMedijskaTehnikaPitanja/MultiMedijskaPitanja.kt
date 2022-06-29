package com.example.barrycards.multiMedijskaTehnikaPitanja

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Multimedija")
class MultiMedijskaPitanja(
    @field:ColumnInfo(name = "Pitanje") var pitanje: String,
    @field:ColumnInfo(name = "Odgovor") var odgovor: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}