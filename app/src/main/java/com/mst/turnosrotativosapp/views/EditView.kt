package com.mst.turnosrotativosapp.views

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mst.turnosrotativosapp.components.FloatButton
import com.mst.turnosrotativosapp.components.MainTitle
import com.mst.turnosrotativosapp.viewmodel.PersonalViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditView(navController: NavController, personalVM: PersonalViewModel, personalNombre: String){

    Scaffold(
        topBar = {CenterAlignedTopAppBar(
            title = { MainTitle(title= "Editar Personal", color = MaterialTheme.colorScheme.onBackground )},
            colors = TopAppBarDefaults.topAppBarColors(
                //containerColor = MaterialTheme.colorScheme.,
                titleContentColor = MaterialTheme.colorScheme.primary
            )
        )
        },
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        floatingActionButton = {
            FloatButton { navController.navigate("AddView") }
        }
    ) {

            EditContent(it,personalNombre,personalVM)



    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun EditContent(paddingValues: PaddingValues, personalNombre: String, personalVM: PersonalViewModel){
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {




        Text(text = "Cambiar fecha: ", modifier = Modifier.padding(top = 20.dp))

        //MyCard()

    }

}


@Composable
fun MyCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { /* Handle click */ },
        color = MaterialTheme.colorScheme.surface, // Color de fondo del tema
        //elevation = 4.dp, // Sombra
        shape = RoundedCornerShape(8.dp) // Forma redondeada
    ) {
        Text(
            text = "Este es el contenido de mi tarjeta.",
            modifier = Modifier.padding(16.dp)
        )
    }
}