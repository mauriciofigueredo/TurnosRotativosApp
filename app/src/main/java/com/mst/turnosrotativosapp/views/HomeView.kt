package com.mst.turnosrotativosapp.views



import android.R.attr.onClick
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.mst.turnosrotativosapp.components.FloatButton
import com.mst.turnosrotativosapp.components.MainTitle
import com.mst.turnosrotativosapp.components.personalCard
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
        val personalList by personalVM.personalList.collectAsState()

        LazyColumn {
            items(personalList) { item ->
                personalCard(item.nombre, item.fecha_ini)
            }
            }
        }






}