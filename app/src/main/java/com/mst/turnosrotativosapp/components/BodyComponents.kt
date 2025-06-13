package com.mst.turnosrotativosapp.components


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.rememberTooltipState
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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



fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalCard(nombre: String, turno: String, dia: String, onClick: ()->Unit) {
    var showConfirmDelete by remember{ mutableStateOf(false)}

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp)
    ) {


        Row(horizontalArrangement = Arrangement.Center) {
            Text(
                text = nombre,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp).fillMaxWidth()
            )
        }


        Row(
            modifier = Modifier
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_group_24),
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier.padding(start = 10.dp)
            )
            Spacer(modifier = Modifier.padding(horizontal = 25.dp))

            //Turno
            Text(text = turno, fontSize = 20.sp, color = Color.Gray)
            Spacer(modifier = Modifier.padding(horizontal = 25.dp))

            //Dia
            Text(text = "Dia: $dia", fontSize = 20.sp, color = Color.Gray)
            Spacer(modifier = Modifier.padding(horizontal = 15.dp))

            //Borrar
            IconButton(onClick={ showConfirmDelete = true}
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Borrar")
            }
            ConfirmationAlertDialog(showConfirmDelete, onConfirm = {showConfirmDelete = false}, onDismiss = { showConfirmDelete=false})

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
}

