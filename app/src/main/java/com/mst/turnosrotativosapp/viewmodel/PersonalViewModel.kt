package com.mst.turnosrotativosapp.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.mst.turnosrotativosapp.model.Personal
import com.mst.turnosrotativosapp.repository.PersonalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PersonalViewModel @Inject constructor(private val repository: PersonalRepository) : ViewModel() {
    private val _personalList = MutableStateFlow<List<Personal>>(emptyList())
    val personalList = _personalList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll().collect { item ->
                if(item.isNullOrEmpty()){
                    _personalList.value = emptyList()
                }else{
                    _personalList.value = item
                }

            }
        }
    }

    fun addPersonal(personal: Personal) = viewModelScope.launch{ repository.addPersonal(personal) }
    fun updatePersonal(personal: Personal) = viewModelScope.launch{ repository.updatePersonal(personal) }
    fun deletePersonal(personal: Personal) = viewModelScope.launch{ repository.deletePersonal(personal) }

}