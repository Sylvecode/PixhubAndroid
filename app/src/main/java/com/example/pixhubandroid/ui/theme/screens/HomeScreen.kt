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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Horizontal
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.computeHorizontalBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pixhubandroid.R
import com.example.pixhubandroid.ui.theme.PixhubAndroidTheme
import com.example.pixhubandroid.ui.theme.Routes
import com.example.pixhubandroid.viewmodel.HomeViewModel
import com.example.pixhubandroid.viewmodel.UserViewModel


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    //Il faut remplacer NomVotreAppliTheme par le thème de votre application
    //Utilisé par exemple dans MainActivity.kt sous setContent {...}
    PixhubAndroidTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val homeViewModel: HomeViewModel = viewModel()
            val userViewModel: UserViewModel = viewModel()

            HomeScreen(homeViewModel = homeViewModel, userViewModel = userViewModel)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController? = null,
    homeViewModel: HomeViewModel,
    userViewModel: UserViewModel
) {


    Box(
        modifier = Modifier
            .background(color = Color(0xFF1E2535))
            .padding(25.dp)
    ) {
        // Ajouter l'image en arrière-plan


        Column(
            modifier = Modifier
                .background(color = Color(0xFF1E2535))
        ) {


            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {

                Image(
                    painter = painterResource(id = R.drawable.logopixhubandroid),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )

                Spacer(modifier = Modifier.width(55.dp)) // Espace pour répartir entre les images

                // Image au centre
                Image(
                    painter = painterResource(id = R.drawable.pixhubtitle),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(85.dp)
                        .align(Alignment.CenterVertically)
                )

                Spacer(Modifier.height(90.dp))

            }


            Spacer(Modifier.height(60.dp))

            Text(
                text = "Accueil",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                //color = MaterialTheme.colorScheme.primary
                color = Color.LightGray
            )


            Spacer(Modifier.height(120.dp))

            Text(
                text = if (userViewModel.user.value.username != "") "Bonjour " + userViewModel.user.value.username else "Veuillez vous connecter",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = Color.LightGray
            )

            Spacer(Modifier.height(20.dp))

            Button(
                modifier = Modifier
                    .padding(15.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = { navHostController?.navigate(Routes.LoginScreen.route) },
                shape = RoundedCornerShape(14.dp),
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {

                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Login")
            }
            Spacer(Modifier.height(10.dp))

        }
    }
}







