package com.mst.turnosrotativosapp.viewmodel


import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mst.turnosrotativosapp.model.Personal
import com.mst.turnosrotativosapp.repository.PersonalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.Date
import javax.inject.Inject


@OptIn(ExperimentalMaterial3Api::class)
@HiltViewModel
class PersonalViewModel @Inject constructor(private val repository: PersonalRepository) : ViewModel() {
    private val _personalList = MutableStateFlow<List<Personal>>(emptyList())
    val personalList = _personalList.asStateFlow()

    //Variable para manipular los valores que va ingresando el usuario
    var personal by mutableStateOf(PersonalState())
        private set

    var _fechaEvaluar = mutableStateOf(Date().time)
        private set

    val fechaActual: LocalDate = LocalDate.now()

    private val _selectedDate = MutableStateFlow<LocalDate>(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate.asStateFlow()

    val fechaEvaluar = _fechaEvaluar.value

    fun selectedDateChange(fecha: LocalDate) {
        _selectedDate.value = fecha
    }


    fun onValueChange(value: String, campo: String) {
        personal = personal.copy(nombre = value)
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _selectedDate.value = LocalDate.now()
            repository.getAll().collect { item ->
                if (item.isEmpty()) {
                    _personalList.value = emptyList()
                } else {
                    _personalList.value = item
                }
            }
        }
    }

    fun addPersonal(personal: Personal) =
        viewModelScope.launch { repository.addPersonal(personal) }

    fun updatePersonal(personal: Personal) =
        viewModelScope.launch { repository.updatePersonal(personal) }

    fun deletePersonal(personal: Personal) =
        viewModelScope.launch { repository.deletePersonal(personal) }


        fun calcularTurnos(): List<TurnoState> {
            val turnos = mutableListOf<TurnoState>()
            viewModelScope.launch {

                personalList.collect {
                    it.forEach { item ->
                        var fechaTurno: Long = item.fecha_ini.toLong()
                        //val fechaActual = now().atZone(ZoneOffset.UTC)?.toInstant()?.toEpochMilli()
                        var fechaActual = Date().time
                        var diff = fechaActual.minus(fechaTurno)
                        var dia: Int = calculoDia(diff)
                        println("CantDias $dia")


                        turnos.add(
                            TurnoState(
                                nombre = item.nombre,
                                turno = "Mañana",
                                dia = dia.toString()
                            )
                        )
                    }
                }
            }
            println("TurnosArray $turnos")
            println("TrunosLength ${turnos.size}")
            return turnos
        }


        fun updateSelectedDate(fecha: LocalDate): LocalDate {
            val fechaInicial = fecha.atStartOfDay()
            val millis = fechaInicial.toInstant(ZoneOffset.UTC).toEpochMilli()
            return millis.let {
                val instant = Instant.ofEpochMilli(it)
                instant.atZone(ZoneOffset.UTC).toLocalDate()
            }


        }




}


    fun calculoDia(diff: Long): Int {
        val dia = (diff / 83400000).toInt()
        return dia
    }
//-----------------------



