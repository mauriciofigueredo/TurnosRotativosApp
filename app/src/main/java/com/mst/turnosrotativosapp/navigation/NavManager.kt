package com.mst.turnosrotativosapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mst.turnosrotativosapp.viewmodel.PersonalViewModel
import com.mst.turnosrotativosapp.views.AddView

import com.mst.turnosrotativosapp.views.HomeView


@Composable
fun NavManager(personalVM: PersonalViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(navController, personalVM)
        }
        composable("AddView"){
            AddView(navController, personalVM)
        }
    }
}
