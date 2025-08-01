package com.mst.turnosrotativosapp.components


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mst.turnosrotativosapp.R
import com.mst.turnosrotativosapp.viewmodel.PersonalViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun MainTitle(title: String, color: Color ) {
    Text(text = title, color = color, fontWeight = FontWeight.Bold)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTextField(value: String, onValueChange: (String) -> Unit, label: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        modifier = Modifier
            .padding(bottom = 15.dp)
            .fillMaxWidth()

    )
}



//fun convertMillisToDate(millis: Long): String {
//    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
//    return formatter.format(Date(millis))
//}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalCard(id:Long, nombre: String, turno: String, dia: String, personalVM: PersonalViewModel) {
    var showConfirmDelete by remember{ mutableStateOf(false)}

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 8.dp)
            .height(80.dp)
    ) {


        Row() {
            Text(
                text = nombre,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            )
        }


        Row(
            modifier = Modifier
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround

        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_group_24),
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.padding(horizontal = 25.dp))

            //Text Turno
            Text(text = turno, fontSize = 20.sp, color = MaterialTheme.colorScheme.secondary)
            Spacer(modifier = Modifier.padding(horizontal = 25.dp))

            //Text Dia
            Text(text = "Dia: $dia", fontSize = 20.sp, color = MaterialTheme.colorScheme.secondary)
            Spacer(modifier = Modifier.padding(horizontal = 15.dp))

            //Icon Borrar
            IconButton(onClick={ showConfirmDelete = true},
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Borrar", tint = Color.Gray)
            }
            ConfirmationAlertDialog(
                showConfirmDelete,
                onConfirm = {
                    runBlocking {
                        val myJob = launch(Dispatchers.IO) {
                            personalVM.deletePersonal(id)
                    }
                    }
                    showConfirmDelete = false
                },
                onDismiss = { showConfirmDelete=false})


        }
    }


}

@Composable
fun ConfirmationAlertDialog(
    showDialog: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                // Se invoca cuando el usuario intenta descartar el diálogo haciendo clic fuera
                // o presionando el botón "atrás".
                onDismiss()
            },
            title = {
                Text(text = "Confirmar eliminación")
            },
            text = {
                Text(text = "¿Estás seguro de que quieres eliminar este registro? Esta acción no se puede deshacer.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Confirmar", color = Color.White)
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}//ConfirmationAlertDialog



