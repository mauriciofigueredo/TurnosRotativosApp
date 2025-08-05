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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.mst.turnosrotativosapp.navigation.NavManager
import com.mst.turnosrotativosapp.ui.theme.TurnosRotativosAppTheme
import com.mst.turnosrotativosapp.viewmodel.PersonalViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var keepSplashScreenOn = true

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition { keepSplashScreenOn }

        // Simula una carga de datos o inicialización
        // En una aplicación real, esto podría ser una llamada a una API, carga de base de datos, etc.
        // Puedes usar un Handler o Coroutines para esto.
        lifecycleScope.launch {
            delay(700)
            keepSplashScreenOn = false
        }

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


