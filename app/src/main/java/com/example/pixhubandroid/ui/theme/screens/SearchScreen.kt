package com.example.pixhubandroid.ui.theme.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.pixhubandroid.viewmodel.SearchViewModel


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenPreview() {
    //Il faut remplacer NomVotreAppliTheme par le thème de votre application
    //Utilisé par exemple dans MainActivity.kt sous setContent {...}
    PixhubAndroidTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val searchViewModel: SearchViewModel = viewModel()
            val navHostController = rememberNavController()


            SearchScreen(navHostController = navHostController, searchViewModel = searchViewModel)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navHostController: NavHostController? = null,
    searchViewModel: SearchViewModel
) {

    // Charger les détails du film en fonction de l'ID
    LaunchedEffect(key1 = "") {
        searchViewModel.loadSearchData()
    }

    val mediaList = searchViewModel.mediaList
    val artistList = searchViewModel.artistList

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
                .padding(innerPadding)
                .padding(25.dp)
                .fillMaxSize()
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


                Spacer(Modifier.height(50.dp))

                Text(
                    text = "Rechercher",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (navHostController != null) {
                                searchViewModel.loadSearchData()
                            }
                        },
                    color = Color.LightGray
                )

                Spacer(Modifier.height(50.dp))

                val textState = rememberSaveable { mutableStateOf("") }
                TextField(
                    value = searchViewModel.query.value,
                    onValueChange = { searchViewModel.query.value = it },
                    placeholder = { Text(text = "Rechercher un film, un acteur...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = TextFieldDefaults.colors(
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(Modifier.height(50.dp))

                // Afficher les noms des médias ou des artistes
                if (mediaList != null && mediaList.isNotEmpty()) {
                    Text(
                        text = mediaList.joinToString(", ") {
                            it.name ?: "Nom inconnu"
                        }, // Remplace les noms nulls par "Nom inconnu"
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                } else if (artistList != null && artistList.isNotEmpty()) {
                    Text(
                        text = artistList.joinToString(", ") {
                            it.name ?: "Nom inconnu"
                        }, // Remplace les noms nulls par "Nom inconnu"
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                } else {
                    Text(
                        text = "Aucun résultat",
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )


                }
            }
        }
    }
}

