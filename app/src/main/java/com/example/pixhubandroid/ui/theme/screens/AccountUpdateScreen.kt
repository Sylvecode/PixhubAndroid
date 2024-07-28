package com.example.pixhubandroid.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pixhubandroid.R
import com.example.pixhubandroid.model.SessionManager
import com.example.pixhubandroid.ui.theme.PixhubAndroidTheme
import com.example.pixhubandroid.ui.theme.Routes
import com.example.pixhubandroid.viewmodel.HomeViewModel
import com.example.pixhubandroid.viewmodel.PixhubViewModel
import com.example.pixhubandroid.viewmodel.UserViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AccountUpdateScreenPreview() {
    PixhubAndroidTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val pixhubViewModel: PixhubViewModel = viewModel()
            val homeViewModel: HomeViewModel = viewModel()
            val userViewModel: UserViewModel = viewModel()
            val navHostController = rememberNavController()

            AccountUpdateScreen(navHostController = navHostController, pixhubViewModel, homeViewModel, userViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountUpdateScreen(
    navHostController: NavHostController? = null,
    pixhubViewModel: PixhubViewModel,
    homeViewModel: HomeViewModel,
    userViewModel: UserViewModel
) {

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
                .padding(25.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.background(
                    color = Color(0xFF1E2535),
                )
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

                Spacer(Modifier.height(30.dp))

                Text(
                    text = "Modifier mes informations de compte",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.LightGray
                )

                Spacer(Modifier.height(10.dp))


                val textState = remember { mutableStateOf("") }

                Spacer(Modifier.height(10.dp))

                Column(
                    modifier = Modifier.padding(35.dp)

                ) {

                    Spacer(Modifier.height(10.dp))

                    if (account != null) {
                        TextField(
                            value = pixhubViewModel.familyNameText.value,
                            onValueChange = { pixhubViewModel.familyNameText.value = it },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            enabled = true,
                            readOnly = false,
                            textStyle = TextStyle.Default,
                            label = { Text("Nom") },
                            trailingIcon = { Icon(Icons.Default.Clear, contentDescription = null) },
                            singleLine = true,
                            maxLines = 1
                        )
                    }

                    Spacer(Modifier.height(10.dp))

                    if (account != null) {
                        TextField(
                            value = pixhubViewModel.nameText.value,
                            onValueChange = { pixhubViewModel.nameText.value = it },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            enabled = true,
                            readOnly = false,
                            textStyle = TextStyle.Default,
                            label = { Text("Prénom") },
                            trailingIcon = { Icon(Icons.Default.Clear, contentDescription = null) },
                            singleLine = true,
                            maxLines = 1
                        )
                    }

                    Spacer(Modifier.height(10.dp))




                    Spacer(Modifier.height(30.dp))

                    if (account != null) {
                        Button(
                            onClick = {
                                account.id?.let {
                                    if (navHostController != null) {
                                        pixhubViewModel.updateAccount(navHostController.context, it)
                                    }
                                    navHostController?.navigate(Routes.AccountScreen.route)
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                            colors = ButtonDefaults.buttonColors(
                                Color(0xFF55F879),
                                contentColor = Color.Black
                            )
                        ) {

                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text("Valider", fontSize = 16.sp)
                        }
                    }

                }
            }
        }
    }
}
