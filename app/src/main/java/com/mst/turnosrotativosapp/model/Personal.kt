package com.mst.turnosrotativosapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "personal")
data class Personal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nombre : String,
    val fecha_ini : Long
)
