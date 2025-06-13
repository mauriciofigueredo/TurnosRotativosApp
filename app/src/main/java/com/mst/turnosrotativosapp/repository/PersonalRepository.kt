package com.mst.turnosrotativosapp.repository

import com.mst.turnosrotativosapp.model.Personal
import com.mst.turnosrotativosapp.room.PersonalDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PersonalRepository @Inject constructor(private val personalDao: PersonalDao)  {
    //Aqui se mapean los metodos que se usaran en el viewmodel
    suspend fun addPersonal(personal: Personal) = personalDao.insertPersonal(personal)
    suspend fun updatePersonal(personal: Personal) = personalDao.updatePersonal(personal)
    suspend fun deletePersonal(personal: Personal) = personalDao.deletePersonal(personal)
    fun getAll(): Flow<List<Personal>> = personalDao.getAll().flowOn(Dispatchers.IO).conflate()
    fun getPersonalById(id: Long): Flow<Personal> = personalDao.getById(id).flowOn(Dispatchers.IO).conflate()
    fun getPersonalByNombre(nombre: String): Flow<Personal> = personalDao.getByNombre(nombre).flowOn(Dispatchers.IO).conflate()

}