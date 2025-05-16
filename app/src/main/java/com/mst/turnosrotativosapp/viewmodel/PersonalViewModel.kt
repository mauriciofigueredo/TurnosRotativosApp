package com.mst.turnosrotativosapp.viewmodel


import android.util.Log
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mst.turnosrotativosapp.components.convertMillisToDate

import com.mst.turnosrotativosapp.model.Personal
import com.mst.turnosrotativosapp.repository.PersonalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@OptIn(ExperimentalMaterial3Api::class)
@HiltViewModel
class PersonalViewModel @Inject constructor(private val repository: PersonalRepository) : ViewModel() {
    private val _personalList = MutableStateFlow<List<Personal>>(emptyList())
    val personalList = _personalList.asStateFlow()
    

    //Variable para manipular los valores que va ingresando el usuario
    var personal by mutableStateOf(PersonalState())
        private set

    fun onValueChange(value: String, campo: String){
        if(campo=="nombre"){
            personal = personal.copy(nombre=value)
        }else{
            personal = personal.copy(fecha = value)

        }
    }


    //--Fin variables date picker
    init {
        viewModelScope.launch(Dispatchers.IO) {
            var turnos: List<String>
            repository.getAll().collect { item ->
                if(item.isEmpty()){
                    _personalList.value = emptyList()
                }else{

                   //turnos = calcularTurnos(item)
                    _personalList.value = item
                }

            }
        }
    }

    fun addPersonal(personal: Personal) = viewModelScope.launch{ repository.addPersonal(personal)  }
    fun updatePersonal(personal: Personal) = viewModelScope.launch{ repository.updatePersonal(personal) }
    fun deletePersonal(personal: Personal) = viewModelScope.launch{ repository.deletePersonal(personal) }

    //Funcion para calcular y devolver listado de turnos
//    fun calcularTurnos(personal: List<Personal>): List<String>{
//        var turno = listOf<String>()
//
//        return turnos
//
//    }

}

