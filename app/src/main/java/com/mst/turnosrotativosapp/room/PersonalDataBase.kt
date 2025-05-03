package com.mst.turnosrotativosapp.room

import androidx.room.Database
import com.mst.turnosrotativosapp.model.Personal


@Database(entities = [Personal::class], version = 1, exportSchema = false)
abstract class PersonalDataBase {
    abstract fun personalDao(): PersonalDao
}