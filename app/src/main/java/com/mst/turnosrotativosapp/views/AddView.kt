package com.mst.turnosrotativosapp.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mst.turnosrotativosapp.components.MainTextField
import com.mst.turnosrotativosapp.components.MainTitle
import com.mst.turnosrotativosapp.model.Personal
import com.mst.turnosrotativosapp.viewmodel.PersonalViewModel
import java.time.Instant
import java.time.ZoneId

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
fun AddContent(paddingValues: PaddingValues, navController: NavController, personalVM: PersonalViewModel)
{
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
            value = personalVM.personal.nombre,
            onValueChange = { personalVM.onValueChange(it, "nombre") },
            label = "Personal"
        )
        Spacer(modifier = Modifier.padding(vertical = 15.dp))

        //-----Date Picker
        var showDate by remember { mutableStateOf(false) }


        Button(onClick = {showDate = !showDate}) { Text("Asignar Fecha") }

        val state = rememberDatePickerState()

        if (showDate){
            DatePickerDialog(
                onDismissRequest = {showDate = !showDate},
                confirmButton = {Button(onClick = {showDate = !showDate}) {Text("Aceptar") }},
                dismissButton = { OutlinedButton(onClick = {showDate = !showDate}) {Text("Cancelar") } }
            ) {
                DatePicker(state = state, title = {
                    Text(text = "Selleccionar fecha", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                },

                    )

            }
        }
        val date = state.selectedDateMillis
        date?.let {
            val selectDate = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            Text(text = "Fecha seleccionada: ${selectDate.dayOfMonth} - ${selectDate.month}")
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            //if(personalVM.personal.nombre.isNotEmpty()){
                FilledTonalButton(
                    onClick = {personalVM.addPersonal(
                        Personal(nombre = personalVM.personal.nombre,
                            fecha_ini = state.selectedDateMillis.toString()
                        ))
                        personalVM.onValueChange("","nombre")
                        navController.popBackStack()},
                    border = BorderStroke(1.dp, Color.Cyan),
                    shape = CircleShape,
                    enabled = personalVM.personal.nombre.isNotEmpty()
                ) { Text("Agregar") }


        }


    }
}