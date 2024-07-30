package com.example.pixhubandroid.ui.theme.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.placeholder
import com.example.pixhubandroid.R
import com.example.pixhubandroid.ui.theme.PixhubAndroidTheme
import com.example.pixhubandroid.viewmodel.ArtistPageViewModel
import com.example.pixhubandroid.viewmodel.CalendarViewModel
import com.example.pixhubandroid.viewmodel.GamePageViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GamePageScreenPreview() {
    PixhubAndroidTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val gamePageViewModel : GamePageViewModel = viewModel()
            val navHostController = rememberNavController()

            GamePageScreen(
                gamePageViewModel = gamePageViewModel,
                navHostController = navHostController
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class, ExperimentalPagerApi::class)
@Composable
fun GamePageScreen(
    gamePageViewModel: GamePageViewModel,
    navHostController: NavHostController? = null,
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    // Charger les détails de l'artiste et ses films
    LaunchedEffect(key1 = "") {
        coroutineScope.launch {
            gamePageViewModel.loadRecentGames()
        }
    }

    val pagerState = rememberPagerState(initialPage = 0)


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
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color(0xFF1E2535))
                    .verticalScroll(scrollState)
            ) {
                Spacer(Modifier.height(30.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp) // Ajustez la hauteur selon vos besoins
                ) {

                    HorizontalPager(
                        count = gamePageViewModel.gameList.size,
                        state = pagerState,
                        modifier = Modifier.fillMaxSize()
                    ) { page ->

                        val game = gamePageViewModel.gameList[page]


                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {


                            Text(
                                text = "Jeux vidéos",
                                fontSize = 10.sp,
                                color = Color.LightGray,
                                modifier = Modifier
                                    .wrapContentWidth(Alignment.End)
                                    .clickable {
                                        if (navHostController != null) {
                                            navHostController.navigate("GamePageScreen")
                                        }
                                    }
                            )



                            Spacer(Modifier.height(12.dp))

                            gamePageViewModel.gameList[page].name.let {
                                if (it != null) {
                                    Text(
                                        text = it,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentWidth(Alignment.CenterHorizontally)
                                            .padding(1.dp),
                                        color = Color.White,
                                        fontSize = 20.sp
                                    )
                                }
                            }




                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp) // Espacement pour séparer les images
                                // Application de RoundedCornerShape au conteneur Box
                            ) {


                                Spacer(Modifier.height(20.dp))

                                com.bumptech.glide.integration.compose.GlideImage(
                                    model = gamePageViewModel.gameList[page].backgroundImage,
                                    contentDescription = "",
                                    // Image d'attente. Permet également de voir l'emplacement de l'image dans la Preview
                                    loading = placeholder(R.mipmap.ic_launcher_round),
                                    // Image d'échec de chargement
                                    failure = placeholder(R.mipmap.ic_launcher),
                                    contentScale = ContentScale.FillWidth,
                                    transition = CrossFade,
                                    //même autres champs qu'une Image classique
                                    modifier = Modifier
                                        .heightIn(max = 500.dp)
                                        .clip(RoundedCornerShape(16.dp))//Sans hauteur il prendra tous l'écran

                                )

                            }
                        }
                    }
                }
            }
        }
    }
}
