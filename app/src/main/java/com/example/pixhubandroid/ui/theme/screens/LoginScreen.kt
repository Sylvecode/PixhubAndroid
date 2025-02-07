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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pixhubandroid.R
import com.example.pixhubandroid.model.AccountBean
import com.example.pixhubandroid.ui.theme.PixhubAndroidTheme
import com.example.pixhubandroid.ui.theme.Routes
import com.example.pixhubandroid.viewmodel.HomeViewModel
import com.example.pixhubandroid.viewmodel.LoginViewModel
import com.example.pixhubandroid.viewmodel.UserViewModel


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    //Il faut remplacer NomVotreAppliTheme par le thème de votre application
    //Utilisé par exemple dans MainActivity.kt sous setContent {...}
    PixhubAndroidTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val loginViewModel: LoginViewModel = viewModel()
            val userViewModel: UserViewModel = viewModel()
            LoginScreen(loginViewModel = loginViewModel, userViewModel = userViewModel)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navHostController: NavHostController? = null,
    loginViewModel: LoginViewModel,
    userViewModel: UserViewModel
) {
    var errorMessage by remember { mutableStateOf<String?>(null) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF1E2535))
            .padding(20.dp)
    ) {
        // Ajouter l'image en arrière-plan


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,


            ) {

            Spacer(Modifier.height(15.dp))
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
                        .weight(1f)
                        .size(110.dp)
                )
            }

            Spacer(modifier = Modifier.size(5.dp))

            Row() {
                // Ajouter un padding entre "FOOT PASSION" et le logo
                Spacer(modifier = Modifier.size(10.dp))

                // Logo
                Image(
                    painter = painterResource(id = R.drawable.pixhubtitle),
                    contentDescription = null, // Modifier en conséquence
                    contentScale = ContentScale.Fit,
                    //   modifier = Modifier.size(50.dp),
                    modifier = Modifier
                        .weight(1f)
                        .size(50.dp)
                )
            }

            Spacer(Modifier.height(40.dp))

            Text(

                text = "Connexion",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(color = Color(0xFFD9D9D9))
            )

            errorMessage?.let { message ->
                Text(
                    text = message,
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(Modifier.height(30.dp))


            TextField(
                label = {
                    Text(
                        text = "Identifiant"
                    )
                },
                shape = RoundedCornerShape(14.dp),
                value = loginViewModel.username.value,
                onValueChange = { loginViewModel.username.value = it },
                colors = TextFieldDefaults.colors(
                    //backgroundColor = Color.Transparent, // Couleur d'arrière-plan transparente
                    focusedIndicatorColor = Color.Transparent, // Couleur de la bordure lorsqu'il est en focus
                    unfocusedIndicatorColor = Color.Transparent // Couleur de la bordure lorsqu'il n'est pas en focus
                ),

                )

            Spacer(Modifier.height(16.dp))

            TextField(
                label = {
                    Text(
                        text = "Mot de passe",
                        fontSize = 16.sp
                    )
                },
                shape = RoundedCornerShape(14.dp),
                value = loginViewModel.password.value,
                onValueChange = { loginViewModel.password.value = it },
                colors = TextFieldDefaults.colors(
                    //backgroundColor = Color.Transparent, // Couleur d'arrière-plan transparente
                    focusedIndicatorColor = Color.Transparent, // Couleur de la bordure lorsqu'il est en focus
                    unfocusedIndicatorColor = Color.Transparent // Couleur de la bordure lorsqu'il n'est pas en focus
                ),

                )

            Spacer(Modifier.height(20.dp))


            Button(
                onClick = {
                    val context = navHostController?.context
                    context?.let { loginViewModel.login(it, userViewModel) }

                },
                shape = RoundedCornerShape(14.dp),
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                colors = ButtonDefaults.buttonColors(Color(0xFF55F879), contentColor = Color.Black),

                ) {

                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Se connecter", fontSize = 16.sp)
            }
            Spacer(Modifier.height(20.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Pas de compte ?  ", color = Color.LightGray, fontSize = 14.sp)
                ClickableText(
                    text = AnnotatedString("Créer un compte"),
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color(0xFF55F879)
                    ),
                    onClick = {navHostController?.navigate(Routes.AccountCreationScreen.route)})


            }
        }
    }
}
