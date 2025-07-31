package com.mst.turnosrotativosapp.views




import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mst.turnosrotativosapp.R
import com.mst.turnosrotativosapp.components.FloatButton
import com.mst.turnosrotativosapp.components.MainTitle
import com.mst.turnosrotativosapp.components.PersonalCard
import com.mst.turnosrotativosapp.viewmodel.PersonalViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, personalVM: PersonalViewModel){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title= "Turnos Redes", color = MaterialTheme.colorScheme.onBackground )},
                colors = TopAppBarDefaults.topAppBarColors(
                    //containerColor = MaterialTheme.colorScheme.,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }, containerColor = MaterialTheme.colorScheme.inversePrimary,
        floatingActionButton = {
            FloatButton { navController.navigate("AddView") }
        }

    ) {
        HomeContent(it, personalVM)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(paddingValues: PaddingValues, personalVM: PersonalViewModel) {
    val context = LocalContext.current

   Column(
        modifier = Modifier
            .padding(paddingValues),
    ) {
        val actualDate by personalVM.selectedDate.collectAsState()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Fecha: ${actualDate.dayOfMonth} ${actualDate.month}",
                fontWeight = FontWeight.W400, fontSize = 20.sp,
                fontFamily = FontFamily.Default,
                color = MaterialTheme.colorScheme.inverseSurface
            )
            Spacer(modifier = Modifier.width(25.dp)) // Espacio entre el icono y el texto

            //---------------- DatePicker
            var showDate by remember { mutableStateOf(false) }
            val datePickerState = rememberDatePickerState()

            IconButton(
                onClick = { showDate = !showDate },
                enabled = true,
                colors = IconButtonDefaults.iconButtonColors(),
            ) {
                Icon(
                    painter = painterResource(R.drawable.calendar),
                    contentDescription = "Calendar",
                )
            }


            if (showDate) {
                DatePickerDialog(
                    onDismissRequest = { showDate = !showDate },
                    confirmButton = {
                        Button(onClick = {
                            showDate = !showDate
                        }) { Text("Aceptar") }
                    },
                    dismissButton = {
                        OutlinedButton(onClick = {
                            showDate = !showDate
                        }) { Text("Cancelar") }
                    }
                ) {
                    DatePicker(
                        state = datePickerState,
                        title = {
                            Text(
                                text = "Selleccionar fecha",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        },

                        )

                }
            }
            val date = datePickerState.selectedDateMillis
            date?.let {


            val selectDate = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
            val fechaActual: LocalDate = LocalDate.now()

            if (fechaActual.isBefore(selectDate)) {
                personalVM.selectedDateChange(
                    Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
                )

            } else {
                showToast(context, "No puede seleccionar una fecha anterior a la de hoy")
            }
        }
            //----------------

        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,

            )

           var lista = personalVM.personalList.collectAsState()


       LazyColumn(modifier = Modifier.fillMaxWidth()) {
           items(lista.value) { item ->
               val (turno, dia) = personalVM.calcularTurno(item)
               PersonalCard(item.id, item.nombre, turno, dia.toString(), personalVM) //item.turno, item.dia, personalVM

           }
       }}



    }







