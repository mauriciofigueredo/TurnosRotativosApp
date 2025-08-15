package com.mst.turnosrotativosapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mst.turnosrotativosapp.model.Personal
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonalDao {

    @Query("select * from personal order by codigo")
    fun getAll(): Flow<List<Personal>>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPersonal(personal: Personal)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePersonal(personal: Personal)

    @Query("delete from personal where id = :id ")
    suspend fun deletePersonal(id: Long)

    @Query("select * from personal where codigo = :codigo")
    fun getByNombre(codigo: String): Flow<Personal?>

}