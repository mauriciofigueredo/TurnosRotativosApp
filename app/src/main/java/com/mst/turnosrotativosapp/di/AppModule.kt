package com.mst.turnosrotativosapp.di

import android.content.Context
import androidx.room.Room
import com.mst.turnosrotativosapp.room.PersonalDao
import com.mst.turnosrotativosapp.room.PersonalDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesPersonalDao(personalDB: PersonalDataBase): PersonalDao{
        return personalDB.personalDao()
    }


    @Singleton
    @Provides
    fun providesPersonalDB(@ApplicationContext context: Context): PersonalDataBase{
        return Room.databaseBuilder(
            context,
            PersonalDataBase::class.java, "personal.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}