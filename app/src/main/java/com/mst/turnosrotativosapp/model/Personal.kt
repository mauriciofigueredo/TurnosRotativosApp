package com.mst.turnosrotativosapp.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey



@Entity(tableName = "personal", indices = [Index(value = ["codigo"], unique = true)])
data class Personal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val sector: String = "",
    val codigo : Int = 0,
    val fechaIni : String
)
