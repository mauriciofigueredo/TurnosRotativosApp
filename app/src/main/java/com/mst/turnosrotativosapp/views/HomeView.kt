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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mst.turnosrotativosapp.components.FloatButton
import com.mst.turnosrotativosapp.components.MainTitle
import com.mst.turnosrotativosapp.viewmodel.PersonalViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, personalVM: PersonalViewModel){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle("Home")}
            )
        }, containerColor = Color.DarkGray,
        floatingActionButton = {
            FloatButton { navController.navigate("AddView") }
        }

    ) {
        HomeContent(it, navController, personalVM)
    }
}

@Composable
fun HomeContent(paddingValues: PaddingValues, navController: NavController, personalVM: PersonalViewModel){
    Column(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        Text("Home View")

    }
}