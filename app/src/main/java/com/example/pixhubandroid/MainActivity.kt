package com.example.pixhubandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pixhubandroid.ui.theme.AppNavigation
import com.example.pixhubandroid.ui.theme.PixhubAndroidTheme
import com.example.pixhubandroid.viewmodel.PixhubViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixhubAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val pixhubViewModel: PixhubViewModel = viewModel()
                    val navHostController : NavHostController = rememberNavController()
                    //AccountCreationScreen(navHostController, pixhubViewModel = pixhubViewModel)
                    AppNavigation()
                }
            }
        }
    }
}
