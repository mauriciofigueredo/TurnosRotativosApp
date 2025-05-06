package com.mst.turnosrotativosapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mst.turnosrotativosapp.components.MainTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navController: NavController) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(
            title = { MainTitle("Add Personal") }
        )},

    ) {
        AddContent(it, navController)
    }

}

@Composable
fun AddContent(paddingValues: PaddingValues, navController: NavController){
    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        Text("Pantalla para agregar personal")
    }

}