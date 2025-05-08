package com.mst.turnosrotativosapp.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mst.turnosrotativosapp.components.CircleButton
import com.mst.turnosrotativosapp.components.DatePickerDocked
import com.mst.turnosrotativosapp.components.MainTextField
import com.mst.turnosrotativosapp.components.MainTitle
import com.mst.turnosrotativosapp.components.convertMillisToDate
import com.mst.turnosrotativosapp.model.Personal
import com.mst.turnosrotativosapp.viewmodel.PersonalViewModel
import java.nio.file.WatchEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navController: NavController, personalVM: PersonalViewModel) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(
            title = { MainTitle("Add Personal") }
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
        var showDatePicker by remember { mutableStateOf(false) }
        val datePickerState = rememberDatePickerState()
        val selectedDate = datePickerState.selectedDateMillis?.let {
            convertMillisToDate(it)
        } ?: ""

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = selectedDate,
                onValueChange = { },
                label = { Text("Inicio turno mañana") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = !showDatePicker }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select date"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
            )

            if (showDatePicker) {
                DatePickerDialog (
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = { Button(onClick = {showDatePicker=false}){Text("Confirmar")} },
                    dismissButton = { OutlinedButton(onClick = {showDatePicker=false}) { Text("Cancelar")} }
                ) {

                    Box(
                        modifier = Modifier
                            //.fillMaxWidth()
                            .offset(y = 1.dp)
                            .shadow(elevation = 1.dp)
                            .background(MaterialTheme.colorScheme.background)
                            .padding(bottom = 12.dp)
                    ) {
                        DatePicker(
                            state = datePickerState,
                            title = {Text(text = "1er Turnno por la mañana", fontSize = 25.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp).padding(start = 8.dp))},
                            showModeToggle = false,

                            )
                    }
                }
            }
        }
        //DatePickerDocked()

        //-----
        Spacer(modifier = Modifier.padding(vertical = 35.dp))

        FilledTonalButton(
            onClick = {personalVM.addPersonal(
                Personal(nombre = personalVM.personal.nombre,
                    fecha_ini = selectedDate.toString()
            ))
                      navController.popBackStack()},
            border = BorderStroke(1.dp, Color.Cyan),
            shape = CircleShape
        ) { Text("Agregar") }

    }
}