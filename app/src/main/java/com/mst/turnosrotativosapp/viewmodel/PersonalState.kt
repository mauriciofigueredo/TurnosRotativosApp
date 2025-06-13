package com.mst.turnosrotativosapp.viewmodel

data class PersonalState(
    val nombre: String = "",
    val fecha: String = ""
)


data class TurnoState(
    val id : Long,
    val nombre : String,
    val turno: String,
    val dia: String
)