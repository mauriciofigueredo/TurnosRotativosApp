package com.mst.turnosrotativosapp.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mst.turnosrotativosapp.components.MainTextField
import com.mst.turnosrotativosapp.components.MainTitle
import com.mst.turnosrotativosapp.model.Personal
import com.mst.turnosrotativosapp.viewmodel.PersonalViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navController: NavController, personalVM: PersonalViewModel) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(
            title = { MainTitle(title = "Agregar Personal", color = MaterialTheme.colorScheme.outline) }
        )},

    ) {
        AddContent(it, navController, personalVM)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContent(paddingValues: PaddingValues, navController: NavController, personalVM: PersonalViewModel) {
    val context = LocalContext.current

    var textoCodigo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(
                horizontal = 20.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        MainTextField(
            value = personalVM.personal.sector,
            onValueChange = { it ->
                if (it.length < 2) {
                    println("sector $it") //hasta aqui funciona
                    personalVM.onSectorChange(it.uppercase().trim())
                }
            },
            label = "Sector",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )


        //-------------------Codigo
        MainTextField(

            value = textoCodigo,
            onValueChange = { it ->
                if (it.length < 3) {
                    textoCodigo = it
                }
            },
            label = "ID",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.padding(vertical = 15.dp))

        //-----Date Picker
        var showDate by remember { mutableStateOf(false) }


        Button(onClick = { showDate = !showDate }) { Text("Asignar Fecha") }

        val state = rememberDatePickerState()

        if (showDate) {
            DatePickerDialog(
                onDismissRequest = { showDate = !showDate },
                confirmButton = { Button(onClick = { showDate = !showDate }) { Text("Aceptar") } },
                dismissButton = {
                    OutlinedButton(onClick = {
                        showDate = !showDate
                    }) { Text("Cancelar") }
                }
            ) {
                DatePicker(
                    state = state,
                    title = {
                        Text(
                            text = "Seleccionar fecha",
                            modifier = Modifier.padding(start = 10.dp, top = 8.dp),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    )
            }
        }
        val date = state.selectedDateMillis
        date?.let {

            val selectDate = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
            val locale = Locale.getDefault()
            var mes = selectDate.month.getDisplayName(java.time.format.TextStyle.FULL, locale)
                .capitalize()

            val fechaActual: LocalDate = LocalDate.now()
            if (selectDate <= fechaActual) {
                Spacer(modifier = Modifier.padding(vertical = 20.dp))

                Text(text = "Fecha seleccionada: ${selectDate.dayOfMonth} - $mes", fontSize = 18.sp)
                Spacer(modifier = Modifier.padding(vertical = 20.dp))

                FilledTonalButton(
                    onClick = {
                        try {
                            personalVM.addPersonal(
                                Personal(
                                    sector = personalVM.personal.sector,
                                    codigo = textoCodigo.trim().toInt(),
                                    fechaIni = selectDate.toString()
                                )
                            )
                        }catch (e: Error){
                            println("Error Add ${e.message}")
                        }
                        try {
                            personalVM.onSectorChange("")
                            navController.popBackStack()
                        }catch (e: Error){
                            println("Error goBack ${e.message}")
                        }
                    },
                    border = BorderStroke(1.dp, Color.Cyan),
                    shape = CircleShape,
                    enabled = textoCodigo.toInt() > 0
                ) { Text("Agregar") }
            } else {
                showToast(context, "Debe seleccionar una fecha anterior a la de hoy")
            }
        }
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}