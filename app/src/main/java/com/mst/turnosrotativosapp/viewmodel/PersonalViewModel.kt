package com.mst.turnosrotativosapp.viewmodel


import android.util.Log
import android.util.Log.e
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mst.turnosrotativosapp.model.Personal
import com.mst.turnosrotativosapp.repository.PersonalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
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


    private val _selectedDate = MutableStateFlow<LocalDate>(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate.asStateFlow()


    fun selectedDateChange(fecha: LocalDate) {
        _selectedDate.value = fecha
        //fechaEvaluar = fecha
    }


    fun onValueChange(value: String) {
        personal = personal.copy(nombre = value)
    }

    init {
        try {
            // Your IPC call here, e.g.,
            viewModelScope.launch{
                //_selectedDate.value = LocalDate.now()
                repository.getAll().collect { item ->
                    if (item.isEmpty()) {
                        _personalList.value = emptyList()
                    } else {
                        _personalList.value = item
                    }
                }
            }

        } catch (e: android.os.DeadObjectException) {
            // The remote object died, handle it (e.g., re-bind to the service, show an error)
            Log.e("Init VM", "Service died: ${e.message}")
            // Consider re-binding the service if appropriate

        } catch (e: Exception) {
            // Handle other potential exceptions
            Log.e("Init VM", "Other error during IPC: ${e.message}")
        }

    }

    fun addPersonal(personal: Personal) =
        viewModelScope.launch { repository.addPersonal(personal) }


    fun deletePersonal(id: Long) =
        viewModelScope.launch { repository.deletePersonal(id) }


        fun calcularTurno(personal: Personal): Pair<String, Int> {
            var turno: String = ""
            var dia: Int = 0

                            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                            val fechaTurno: LocalDate = LocalDate.parse(personal.fecha_ini, formatter)
                            val diff: Long = ChronoUnit.DAYS.between(fechaTurno, selectedDate.value )
                            println("diff dias: $diff")
                            dia = calculoDia(diff)
                            val resto: Int = diff.toInt() % 6

                            if(resto == 0 ){
                                turno = "Mañana"
                            } else{
                                turno = "Tarde"
                            }
            return Pair(turno, dia)
        }

}

    fun calculoDia(diff: Long): Int {
        //val dia = (diff / 83400000).toInt()
        //val diaEnCiclo = (dia - 1) % 6 + 1 // dia del gemini
        val diaEnCiclo = (diff.toInt() ) % 6 +1
        println("Dife -> $diaEnCiclo")
        return diaEnCiclo
    }
//-----------------------



