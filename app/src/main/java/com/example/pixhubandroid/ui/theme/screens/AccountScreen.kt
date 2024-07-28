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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.pixhubandroid.ui.theme.Routes
import com.example.pixhubandroid.viewmodel.HomeViewModel
import com.example.pixhubandroid.viewmodel.PixhubViewModel
import com.example.pixhubandroid.viewmodel.UserViewModel


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AccountScreenPreview() {
    //Il faut remplacer NomVotreAppliTheme par le thème de votre application
    //Utilisé par exemple dans MainActivity.kt sous setContent {...}
    PixhubAndroidTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val homeViewModel: HomeViewModel = viewModel()
            val userViewModel: UserViewModel = viewModel()
            val pixhubViewModel: PixhubViewModel = viewModel()
            val navHostController = rememberNavController()

            AccountScreen(
                pixhubViewModel,
                navHostController = navHostController,
                homeViewModel = homeViewModel,
                userViewModel = userViewModel
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    pixhubViewModel: PixhubViewModel,
    navHostController: NavHostController? = null,
    homeViewModel: HomeViewModel,
    userViewModel: UserViewModel
) {
// Observer les changements dans l'état de l'utilisateur
    val accountState = userViewModel.account.collectAsState(initial = null)
    val account = accountState.value

    Scaffold(
        bottomBar = {
            if (navHostController != null) {
                NavBarDown(
                    navHostController = navHostController
                )
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .background(color = Color(0xFF1E2535))
                .fillMaxSize()
                .padding(25.dp)
        ) {
            // Ajouter l'image en arrière-plan


            Column(
                modifier = Modifier
                    .background(color = Color(0xFF1E2535))
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


                Spacer(Modifier.height(60.dp))

                Text(
                    text = "Mon compte",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth(),
                    //color = MaterialTheme.colorScheme.primary
                    color = Color.LightGray
                )

                Spacer(Modifier.height(90.dp))



                Button(
                    onClick = {
                        navHostController?.navigate(Routes.AccountUpdateScreen.route)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                    colors = ButtonDefaults.buttonColors(
                        Color(111111),
                        contentColor = Color.LightGray
                    )
                ) {

                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Modifier mes informations de compte", fontSize = 16.sp)
                }

                Spacer(Modifier.height(40.dp))


                Button(
                    onClick = {
                        if (account != null) {
                            account.id?.let {
                                if (navHostController != null) {
                                    pixhubViewModel.deleteAccount(navHostController.context, it)
                                }
                                if (navHostController != null) {
                                    userViewModel.logout(navHostController.context)
                                }
                                navHostController?.navigate(Routes.AccountUpdateScreen.route)
                            }
                        }
                    },

                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                    colors = ButtonDefaults.buttonColors(
                        Color(11111),
                        contentColor = Color.Red
                    )
                ) {

                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Supprimer mon compte", fontSize = 16.sp)
                }


            }

        }
    }
}

