package com.example.pixhubandroid.ui.theme.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pixhubandroid.ui.theme.Routes


@Composable
fun NavBarDown(navHostController: NavHostController) {
    // Data class pour représenter un élément de menu
    //data class MenuItem(val title: String, val iconResId: Int, val onClick: () -> Unit)


    BottomAppBar(containerColor = Color(0xFF1E2535),
        contentColor = Color.LightGray,
        contentPadding = PaddingValues(5.dp),
        actions = {
            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(onClick = {navHostController?.navigate(Routes.HomeScreen.route)}) {
                Icon(Icons.Filled.Home, contentDescription = "Accueil")
            }

            Spacer(modifier = Modifier.width(30.dp)) // Espacement entre les boutons


            IconButton(onClick = { navHostController?.navigate(Routes.CalendarScreen.route) }) {
                Icon(
                    Icons.Filled.DateRange,
                    contentDescription = "Calendrier",
                )
            }

            Spacer(modifier = Modifier.width(30.dp)) // Espacement entre les boutons
            IconButton(onClick = {navHostController?.navigate(Routes.SearchScreen.route)}) {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Rechercher",
                )
            }

            Spacer(modifier = Modifier.width(30.dp)) // Espacement entre les boutons

            IconButton(onClick = {navHostController?.navigate(Routes.ProfileScreen.route)}) {
                Icon(
                    Icons.Filled.AccountCircle,
                    contentDescription = "Profil",
                )
            }

            Spacer(modifier = Modifier.weight(1f, true))
        },
    )
}