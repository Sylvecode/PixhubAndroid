package com.example.pixhubandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pixhubandroid.ui.theme.AppNavigation
import com.example.pixhubandroid.ui.theme.PixhubAndroidTheme
import com.example.pixhubandroid.ui.theme.screens.CalendarScreen
import com.example.pixhubandroid.viewmodel.CalendarViewModel
import com.example.pixhubandroid.viewmodel.HomeViewModel
import com.example.pixhubandroid.viewmodel.PixhubViewModel
import com.example.pixhubandroid.viewmodel.UserViewModel

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
                    val userViewModel: UserViewModel = viewModel()
                    val calendarViewModel: CalendarViewModel = viewModel()

                    // Load the account when the app starts
                    LaunchedEffect(Unit) {
                        userViewModel.loadAccount(this@MainActivity)
                    }
                    //val pixhubViewModel: PixhubViewModel = viewModel()
                    val navHostController : NavHostController = rememberNavController()
                    AppNavigation(navHostController, userViewModel)
                    //CalendarScreen(homeViewModel = HomeViewModel(), userViewModel = UserViewModel(), calendarViewModel = CalendarViewModel())
                }
            }
        }
    }
}
