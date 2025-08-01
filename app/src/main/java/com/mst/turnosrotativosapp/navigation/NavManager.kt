package com.mst.turnosrotativosapp.navigation

import android.util.Log
import androidx.compose.material3.Text
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
//        composable("EditView/{personalNombre}"){it ->
//            val personalNombre = it.arguments?.getString("personalNombre")
//            Log.d("ParamEditView","Valor: $personalNombre")
//            if ( personalNombre != null){
//                EditView(navController, personalVM, personalNombre)
//            }else{
//                Text("Error, nombre no encontrado")
//            }
//        }
    }
}
