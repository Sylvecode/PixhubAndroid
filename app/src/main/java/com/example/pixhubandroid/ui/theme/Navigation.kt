package com.example.pixhubandroid.ui.theme

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pixhubandroid.ui.theme.screens.AccountCreationScreen
import com.example.pixhubandroid.ui.theme.screens.HomeScreen
import com.example.pixhubandroid.ui.theme.screens.LoginScreen
import com.example.pixhubandroid.viewmodel.HomeViewModel
import com.example.pixhubandroid.viewmodel.LoginViewModel
import com.example.pixhubandroid.viewmodel.PixhubViewModel
import com.example.pixhubandroid.viewmodel.UserViewModel

sealed class Routes(val route: String) {
    //Route 1
    data object LoginScreen : Routes("LoginScreen")

    //Route 2
    data object AccountCreationScreen : Routes("AccountCreationScreen")

    //Route 3
    data object HomeScreen : Routes("HomeScreen")
}

@Composable
fun AppNavigation() {

    val navHostController : NavHostController = rememberNavController()

    //viewModel appartient au framework permet de récupérer une instance déjà existante s'il en existe une
    val loginViewModel: LoginViewModel = viewModel()
    val pixhubViewModel: PixhubViewModel = viewModel()
    val homeViewModel: HomeViewModel = viewModel()
    val userViewModel: UserViewModel = viewModel()




    //Import version avec Composable
    NavHost(navController = navHostController, startDestination = Routes.LoginScreen.route) {
        //Route 1 vers notre LoginScreen
        composable(Routes.LoginScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            LoginScreen(navHostController, loginViewModel = loginViewModel, userViewModel = userViewModel)
        }

        //Route 2 vers notre AccountCreationScreen
        composable(Routes.AccountCreationScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            AccountCreationScreen(navHostController, pixhubViewModel = pixhubViewModel, userViewModel = userViewModel)
        }

        //Route 3 vers notre HomeScreen
        composable(Routes.HomeScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            HomeScreen(navHostController, homeViewModel = homeViewModel, userViewModel = userViewModel)
        }

    }
}