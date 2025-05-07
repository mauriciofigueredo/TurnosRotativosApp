package com.mst.turnosrotativosapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mst.turnosrotativosapp.components.CircleButton
import com.mst.turnosrotativosapp.components.MainTextField
import com.mst.turnosrotativosapp.components.MainTitle
import com.mst.turnosrotativosapp.model.Personal
import com.mst.turnosrotativosapp.viewmodel.PersonalViewModel

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

@Composable
fun AddContent(paddingValues: PaddingValues, navController: NavController, personalVM: PersonalViewModel){
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainTextField(
            value = personalVM.personal.nombre,
            onValueChange = {personalVM.onValueChange(it,"nombre")},
            label = "Personal"
        )
        MainTextField(
            value = personalVM.personal.fecha,
            onValueChange = {personalVM.onValueChange(it, "fecha")},
            label = "Fecha"
        )

        Spacer(modifier = Modifier.padding(vertical = 15.dp))
        CircleButton(icon = Icons.Default.AddCircle) {
            //personalVM.addPersonal(personalVM.addPersonal(personalVM.personal))
        }
    }

}