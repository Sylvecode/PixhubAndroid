package com.example.pixhubandroid.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pixhubandroid.R
import com.example.pixhubandroid.ui.theme.PixhubAndroidTheme
import com.example.pixhubandroid.viewmodel.CalendarViewModel
import com.example.pixhubandroid.viewmodel.HomeViewModel
import com.example.pixhubandroid.viewmodel.UserViewModel



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalendarScreenPreview() {
    //Il faut remplacer NomVotreAppliTheme par le thème de votre application
    //Utilisé par exemple dans MainActivity.kt sous setContent {...}
    PixhubAndroidTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val homeViewModel: HomeViewModel = viewModel()
            val userViewModel: UserViewModel = viewModel()
            val calendarViewModel: CalendarViewModel = viewModel()
            val navHostController = rememberNavController()

            CalendarScreen(navHostController = navHostController, homeViewModel = homeViewModel, userViewModel = userViewModel, calendarViewModel = calendarViewModel)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    navHostController: NavHostController? = null,
    homeViewModel: HomeViewModel,
    userViewModel: UserViewModel,
    calendarViewModel: CalendarViewModel
) {

    Scaffold (
        bottomBar =  {
            if (navHostController != null) {
                NavBarDown(navHostController = navHostController)
            }
        }
    ){ innerPadding ->
        Box(
            modifier = Modifier
                .background(color = Color(0xFF1E2535))
                .padding(25.dp)
        ) {
            Surface {
                Column(
                    modifier = Modifier
                        .background(color = Color(0xFF1E2535))
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center // Centre le contenu dans la Box
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pixhubtitle),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(65.dp)
                        )
                    }

                    Spacer(Modifier.height(10.dp))





                    if (navHostController != null) {
                        Slider(calendarViewModel = calendarViewModel, navHostController = navHostController)
                    }


                }




            }
        }
    }

}
