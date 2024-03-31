package com.example.pixhubandroid.ui.theme.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            val userViewModel: UserViewModel = viewModel()
            pixhubViewModel.usernameText.value = ""
            pixhubViewModel.familyNameText.value = ""
            pixhubViewModel.nameText.value = ""
            pixhubViewModel.emailText.value = ""
            pixhubViewModel.passwordText.value = ""
            pixhubViewModel.passwordConfirmText.value = ""
            AccountCreationScreen(pixhubViewModel = pixhubViewModel, userViewModel = userViewModel )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountCreationScreen(navHostController: NavHostController? = null, pixhubViewModel: PixhubViewModel, userViewModel: UserViewModel) {

    Box(modifier = Modifier.fillMaxSize()) {
        // Ajouter l'image en arrière-plan


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,


            ) {

            Spacer(Modifier.height(30.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            )
            {


                // Logo
                Image(
                    painter = painterResource(id = R.drawable.logopixhubandroid),
                    contentDescription = null, // Modifier en conséquence
                    contentScale = ContentScale.Fit,
                    //   modifier = Modifier.size(50.dp),
                    modifier = Modifier
                        .weight(2f)
                        .size(80.dp)
                )


                Text(
                    text = "pixhub",
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(3f),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )

            }

            Spacer(Modifier.height(30.dp))

            Text(
                text = "Création de compte",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                //color = MaterialTheme.colorScheme.primary
                color = Color.Black
            )

            Spacer(Modifier.height(5.dp))


            // Encart avec fond blanc et bord arrondi
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.padding(35.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Spacer(Modifier.height(8.dp))

                    // Champ de texte pour l'équipe 1
                    TextField(
                        label = {
                                Text(text="Identifiant")
                        },
                        shape = RoundedCornerShape(14.dp),
                        value = pixhubViewModel.usernameText.value,
                        onValueChange = {pixhubViewModel.usernameText.value = it},
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            //backgroundColor = Color.Transparent, // Couleur d'arrière-plan transparente
                            focusedIndicatorColor = Color.Transparent, // Couleur de la bordure lorsqu'il est en focus
                            unfocusedIndicatorColor = Color.Transparent // Couleur de la bordure lorsqu'il n'est pas en focus
                        ),
                    )
                    Spacer(Modifier.height(16.dp))

                    TextField(
                        label = {
                            Text(text="Nom")
                        },
                        shape = RoundedCornerShape(14.dp),
                        value = pixhubViewModel.familyNameText.value,
                        onValueChange = {pixhubViewModel.familyNameText.value = it},
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            //backgroundColor = Color.Transparent, // Couleur d'arrière-plan transparente
                            focusedIndicatorColor = Color.Transparent, // Couleur de la bordure lorsqu'il est en focus
                            unfocusedIndicatorColor = Color.Transparent // Couleur de la bordure lorsqu'il n'est pas en focus
                        ),
                    )
                    Spacer(Modifier.height(16.dp))

                    TextField(
                        label = {
                            Text(text="Prénom")
                        },
                        shape = RoundedCornerShape(14.dp),
                        value = pixhubViewModel.nameText.value,
                        onValueChange = {pixhubViewModel.nameText.value = it},
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            //backgroundColor = Color.Transparent, // Couleur d'arrière-plan transparente
                            focusedIndicatorColor = Color.Transparent, // Couleur de la bordure lorsqu'il est en focus
                            unfocusedIndicatorColor = Color.Transparent // Couleur de la bordure lorsqu'il n'est pas en focus
                        ),
                    )
                    Spacer(Modifier.height(16.dp))

                    TextField(
                        label = {
                            Text(text="Email")
                        },
                        shape = RoundedCornerShape(14.dp),
                        value = pixhubViewModel.emailText.value,
                        onValueChange = {pixhubViewModel.emailText.value = it},
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            //backgroundColor = Color.Transparent, // Couleur d'arrière-plan transparente
                            focusedIndicatorColor = Color.Transparent, // Couleur de la bordure lorsqu'il est en focus
                            unfocusedIndicatorColor = Color.Transparent // Couleur de la bordure lorsqu'il n'est pas en focus
                        ),
                    )
                    Spacer(Modifier.height(16.dp))

                    TextField(
                        label = {
                            Text(text="Mot de passe")
                        },
                        shape = RoundedCornerShape(14.dp),
                        value = pixhubViewModel.passwordText.value,
                        onValueChange = {pixhubViewModel.passwordText.value = it},
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            //backgroundColor = Color.Transparent, // Couleur d'arrière-plan transparente
                            focusedIndicatorColor = Color.Transparent, // Couleur de la bordure lorsqu'il est en focus
                            unfocusedIndicatorColor = Color.Transparent // Couleur de la bordure lorsqu'il n'est pas en focus
                        ),
                    )
                    Spacer(Modifier.height(16.dp))

                    TextField(
                        label = {
                            Text(text="Confirmer le mot de passe")
                        },
                        shape = RoundedCornerShape(14.dp),
                        value = pixhubViewModel.passwordConfirmText.value,
                        onValueChange = {pixhubViewModel.passwordConfirmText.value = it},
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            //backgroundColor = Color.Transparent, // Couleur d'arrière-plan transparente
                            focusedIndicatorColor = Color.Transparent, // Couleur de la bordure lorsqu'il est en focus
                            unfocusedIndicatorColor = Color.Transparent // Couleur de la bordure lorsqu'il n'est pas en focus
                        ),
                    )

                    Spacer(Modifier.height(16.dp))

                    Spacer(Modifier.height(16.dp))


                    Spacer(Modifier.height(8.dp))



                    Button(
                        onClick = {pixhubViewModel.addAccount(pixhubViewModel.usernameText.value,
                                pixhubViewModel.familyNameText.value,
                                pixhubViewModel.nameText.value,
                                pixhubViewModel.emailText.value,
                                pixhubViewModel.passwordText.value,
                            pixhubViewModel.passwordConfirmText.value)},
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                    ) {

                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Créer un compte")
                    }
                    Spacer(Modifier.height(1.dp))


                }
            }
        }
    }
}
