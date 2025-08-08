package com.mst.turnosrotativosapp.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey



@Entity(tableName = "personal", indices = [Index(value = ["nombre"], unique = true)])
data class Personal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nombre : String,
    val fechaIni : String
)
