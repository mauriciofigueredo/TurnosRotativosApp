package com.mst.turnosrotativosapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mst.turnosrotativosapp.model.Personal


@Database(entities = [Personal::class], version = 3, exportSchema = false)
abstract class PersonalDataBase: RoomDatabase() {
    abstract fun personalDao(): PersonalDao
}