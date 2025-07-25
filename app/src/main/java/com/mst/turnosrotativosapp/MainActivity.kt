package com.mst.turnosrotativosapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mst.turnosrotativosapp.navigation.NavManager
import com.mst.turnosrotativosapp.ui.theme.TurnosRotativosAppTheme
import com.mst.turnosrotativosapp.viewmodel.PersonalViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val personalVM: PersonalViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            TurnosRotativosAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager(personalVM)

                }
            }
        }
    }
}


