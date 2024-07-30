package com.example.pixhubandroid.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pixhubandroid.ui.theme.screens.AccountCreationScreen
import com.example.pixhubandroid.ui.theme.screens.AccountScreen
import com.example.pixhubandroid.ui.theme.screens.AccountUpdateScreen
import com.example.pixhubandroid.ui.theme.screens.ArtistPageScreen
import com.example.pixhubandroid.ui.theme.screens.CalendarScreen
import com.example.pixhubandroid.ui.theme.screens.GamePageScreen
import com.example.pixhubandroid.ui.theme.screens.HomeScreen
import com.example.pixhubandroid.ui.theme.screens.LoginScreen
import com.example.pixhubandroid.ui.theme.screens.MediaPageScreen
import com.example.pixhubandroid.ui.theme.screens.ProfileScreen
import com.example.pixhubandroid.ui.theme.screens.SearchScreen
import com.example.pixhubandroid.viewmodel.ArtistPageViewModel
import com.example.pixhubandroid.viewmodel.CalendarViewModel
import com.example.pixhubandroid.viewmodel.GamePageViewModel
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

    //Route 4
    data object CalendarScreen : Routes("CalendarScreen")

    //Route 5
    data object SearchScreen : Routes("SearchScreen")

    //Route 6
    data object ProfileScreen : Routes("ProfileScreen")

    //Route 6
    data object AccountScreen : Routes("AccountScreen")

    //Route 7
    data object AccountUpdateScreen : Routes("AccountUpdateScreen")

    //Route 8
    data object MediaPageScreen : Routes("MediaPageScreen/{mediaId}")

    //Route 9
    data object ArtistPageScreen : Routes("ArtistPageScreen/{artistId}")

    //Route 9
    data object GamePageScreen : Routes("GamePageScreen")





}

@Composable
fun AppNavigation(navHostController: NavHostController, userViewModel: UserViewModel) {

    // Charger l'utilisateur connecté une seule fois au lancement de l'application
    LaunchedEffect(Unit) {
        userViewModel.loadAccount(navHostController.context)
    }

    // Observer les changements dans l'état de l'utilisateur
    val accountState = userViewModel.account.collectAsState(initial = null)
    val account = accountState.value


    //viewModel appartient au framework permet de récupérer une instance déjà existante s'il en existe une
    val loginViewModel: LoginViewModel = viewModel()
    val pixhubViewModel: PixhubViewModel = viewModel()
    val homeViewModel: HomeViewModel = viewModel()
    val userViewModel: UserViewModel = viewModel()
    val calendarViewModel: CalendarViewModel = viewModel()
    val artistPageViewModel: ArtistPageViewModel = viewModel()
    val gamePageViewModel: GamePageViewModel = viewModel()


    // Définir la destination de départ en fonction de l'état de l'utilisateur
    val startDestination = if (account != null) Routes.HomeScreen.route else Routes.LoginScreen.route

    //Import version avec Composable
    NavHost(navController = navHostController, startDestination = startDestination) {
        //Route 1 vers notre LoginScreen
        composable(Routes.LoginScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            LoginScreen(navHostController, loginViewModel = loginViewModel, userViewModel = userViewModel)
        }

        //Route 2 vers AccountCreationScreen
        composable(Routes.AccountCreationScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            AccountCreationScreen(navHostController, pixhubViewModel = pixhubViewModel,  loginViewModel = loginViewModel, userViewModel = userViewModel)
        }

        //Route 3 vers HomeScreen
        composable(Routes.HomeScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            HomeScreen(navHostController, homeViewModel = homeViewModel, userViewModel = userViewModel)
        }


        //Route 4 vers CalendarScreen
        composable(Routes.CalendarScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            CalendarScreen(navHostController, homeViewModel = homeViewModel, userViewModel = userViewModel, calendarViewModel = calendarViewModel)
        }


        //Route 5 vers SearchScreen
        composable(Routes.SearchScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            SearchScreen(navHostController, homeViewModel = homeViewModel, userViewModel = userViewModel)
        }



        //Route 6 vers ProfileScreen
        composable(Routes.ProfileScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            ProfileScreen(navHostController, homeViewModel = homeViewModel, userViewModel = userViewModel)
        }

        //Route 7 vers AccountScreen
        composable(Routes.AccountScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            AccountScreen(pixhubViewModel, navHostController, homeViewModel = homeViewModel, userViewModel = userViewModel)
        }

        //Route 8 vers AccountUpdateScreen
        composable(Routes.AccountUpdateScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            AccountUpdateScreen(navHostController, homeViewModel = homeViewModel, userViewModel = userViewModel, pixhubViewModel = pixhubViewModel)
        }


        //Route 9 vers MediaPageScreen
        composable(Routes.MediaPageScreen.route) { backStackEntry ->
            val mediaId = backStackEntry.arguments?.getString("mediaId")?.toIntOrNull() ?: 0
            MediaPageScreen(mediaId = mediaId, calendarViewModel = viewModel(), navHostController = navHostController)
        }

        //Route 10 vers ArtistPageScreen
        composable(Routes.ArtistPageScreen.route) { backStackEntry ->
            val artistId = backStackEntry.arguments?.getString("artistId")?.toIntOrNull() ?: 0
            ArtistPageScreen(artistId = artistId, calendarViewModel = viewModel(), artistPageViewModel = viewModel(), navHostController = navHostController)
        }

        //Route 10 vers GamePageScreen
        composable(Routes.GamePageScreen.route) { backStackEntry ->
            GamePageScreen(gamePageViewModel = viewModel(), navHostController = navHostController)
        }
    }
}