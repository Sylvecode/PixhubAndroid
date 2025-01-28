package com.example.pixhubandroid.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
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
import com.example.pixhubandroid.viewmodel.LoginViewModel
import com.example.pixhubandroid.viewmodel.PixhubViewModel
import com.example.pixhubandroid.viewmodel.UserViewModel


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AccountCreationPreview() {
    //Il faut remplacer NomVotreAppliTheme par le thème de votre application
    //Utilisé par exemple dans MainActivity.kt sous setContent {...}
    PixhubAndroidTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val pixhubViewModel: PixhubViewModel = viewModel()
            val loginViewModel: LoginViewModel = viewModel()
            val userViewModel: UserViewModel = viewModel()

            pixhubViewModel.usernameText.value = ""
            pixhubViewModel.familyNameText.value = ""
            pixhubViewModel.nameText.value = ""
            pixhubViewModel.emailText.value = ""
            pixhubViewModel.passwordText.value = ""
            pixhubViewModel.passwordConfirmText.value = ""
            AccountCreationScreen(
                pixhubViewModel = pixhubViewModel,
                loginViewModel = loginViewModel,
                userViewModel = userViewModel
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountCreationScreen(
    navHostController: NavHostController? = null,
    pixhubViewModel: PixhubViewModel,
    loginViewModel: LoginViewModel,
    userViewModel: UserViewModel
) {

    var errorMessage by remember { mutableStateOf<String?>(null) }



    Box(
        modifier = Modifier
            .background(color = Color(0xFF1E2535))
    ) {
        // Ajouter l'image en arrière-plan


        Box(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                // Image à droite
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
            }
        }



        Surface(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.padding(70.dp),
            color = Color((0xFF1E2535))
        ) {
            Column() {

                Spacer(Modifier.height(40.dp))

                Text(
                    text = "Création de compte",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    //color = MaterialTheme.colorScheme.primary
                    color = Color.LightGray
                )

                errorMessage?.let { message ->
                    Text(
                        text = message,
                        color = Color.Red,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Spacer(Modifier.height(20.dp))

                // Champ de texte pour l'équipe 1
                TextField(
                    label = {
                        Text(text = "Pseudo")
                    },
                    shape = RoundedCornerShape(14.dp),
                    value = pixhubViewModel.usernameText.value,
                    onValueChange = { pixhubViewModel.usernameText.value = it },
                    colors = TextFieldDefaults.colors(
                        //backgroundColor = Color.Transparent, // Couleur d'arrière-plan transparente
                        focusedIndicatorColor = Color.Transparent, // Couleur de la bordure lorsqu'il est en focus
                        unfocusedIndicatorColor = Color.Transparent // Couleur de la bordure lorsqu'il n'est pas en focus
                    ),
                )
                Spacer(Modifier.height(16.dp))

                TextField(
                    label = {
                        Text(text = "Nom")
                    },
                    shape = RoundedCornerShape(14.dp),
                    value = pixhubViewModel.familyNameText.value,
                    onValueChange = { pixhubViewModel.familyNameText.value = it },
                    colors = TextFieldDefaults.colors(
                        //backgroundColor = Color.Transparent, // Couleur d'arrière-plan transparente
                        focusedIndicatorColor = Color.Transparent, // Couleur de la bordure lorsqu'il est en focus
                        unfocusedIndicatorColor = Color.Transparent // Couleur de la bordure lorsqu'il n'est pas en focus
                    ),
                )
                Spacer(Modifier.height(16.dp))

                TextField(
                    label = {
                        Text(text = "Prénom")
                    },
                    shape = RoundedCornerShape(14.dp),
                    value = pixhubViewModel.nameText.value,
                    onValueChange = { pixhubViewModel.nameText.value = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        //backgroundColor = Color.Transparent, // Couleur d'arrière-plan transparente
                        focusedIndicatorColor = Color.Transparent, // Couleur de la bordure lorsqu'il est en focus
                        unfocusedIndicatorColor = Color.Transparent // Couleur de la bordure lorsqu'il n'est pas en focus
                    ),
                )
                Spacer(Modifier.height(16.dp))

                TextField(
                    label = {
                        Text(text = "Email")
                    },
                    shape = RoundedCornerShape(14.dp),
                    value = pixhubViewModel.emailText.value,
                    onValueChange = { pixhubViewModel.emailText.value = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        //backgroundColor = Color.Transparent, // Couleur d'arrière-plan transparente
                        focusedIndicatorColor = Color.Transparent, // Couleur de la bordure lorsqu'il est en focus
                        unfocusedIndicatorColor = Color.Transparent // Couleur de la bordure lorsqu'il n'est pas en focus
                    ),
                )
                Spacer(Modifier.height(16.dp))

                TextField(
                    label = {
                        Text(text = "Mot de passe", fontSize = 16.sp)
                    },
                    shape = RoundedCornerShape(14.dp),
                    value = pixhubViewModel.passwordText.value,
                    onValueChange = { pixhubViewModel.passwordText.value = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        //backgroundColor = Color.Transparent, // Couleur d'arrière-plan transparente
                        focusedIndicatorColor = Color.Transparent, // Couleur de la bordure lorsqu'il est en focus
                        unfocusedIndicatorColor = Color.Transparent // Couleur de la bordure lorsqu'il n'est pas en focus
                    ),
                )
                Spacer(Modifier.height(16.dp))

                TextField(
                    label = {
                        Text(text = "Confirmer le mot de passe", fontSize = 16.sp)
                    },
                    shape = RoundedCornerShape(14.dp),
                    value = pixhubViewModel.passwordConfirmText.value,
                    onValueChange = { pixhubViewModel.passwordConfirmText.value = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        //backgroundColor = Color.Transparent, // Couleur d'arrière-plan transparente
                        focusedIndicatorColor = Color.Transparent, // Couleur de la bordure lorsqu'il est en focus
                        unfocusedIndicatorColor = Color.Transparent // Couleur de la bordure lorsqu'il n'est pas en focus
                    ),
                )

                Spacer(Modifier.height(25.dp))




                Button(
                    onClick = {
                        if (navHostController != null) {
                            pixhubViewModel.addAccount(navHostController.context)
                        }
                        navHostController?.navigate(Routes.HomeScreen.route)
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
                    Text("Créer un compte", fontSize = 16.sp)
                }
                Spacer(Modifier.height(15.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        ClickableText(
                            text = AnnotatedString("J'ai déjà un compte"),
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Color(0xFF55F879),
                                textAlign = TextAlign.Center
                            ),
                            onClick = { navHostController?.navigate(Routes.LoginScreen.route) }
                        )


                    }
                }


            }
        }
    }
}


