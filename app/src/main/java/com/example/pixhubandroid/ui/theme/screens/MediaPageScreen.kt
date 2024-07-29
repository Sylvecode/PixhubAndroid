package com.example.pixhubandroid.ui.theme.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.pixhubandroid.ui.theme.PixhubAndroidTheme
import com.example.pixhubandroid.viewmodel.ArtistPageViewModel
import com.example.pixhubandroid.viewmodel.CalendarViewModel
import com.example.pixhubandroid.viewmodel.HomeViewModel
import com.example.pixhubandroid.viewmodel.UserViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.glide.GlideImage

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MediaPageScreenPreview() {
    PixhubAndroidTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val homeViewModel: HomeViewModel = viewModel()
            val userViewModel: UserViewModel = viewModel()
            val navHostController = rememberNavController()
            val calendarViewModel = CalendarViewModel()


            MediaPageScreen(
                mediaId = 0,
                calendarViewModel = calendarViewModel,
                navHostController = navHostController
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class, ExperimentalPagerApi::class)
@Composable
fun MediaPageScreen(
    mediaId: Int,
    calendarViewModel: CalendarViewModel,
    navHostController: NavHostController? = null,
) {
    // Charger les détails du film en fonction de l'ID
    LaunchedEffect(mediaId) {
        calendarViewModel.loadMovieDetails(mediaId)
        calendarViewModel.loadMovieArtists(mediaId)
    }
    // Obtenez les détails du film depuis le ViewModel
    val media by calendarViewModel.list.firstOrNull { it.id == mediaId }?.let { mutableStateOf(it) }
        ?: mutableStateOf(null)
    val artists by mutableStateOf(calendarViewModel.artistsList)
    val filteredArtists = artists.filter { it.name != null && it.profilePath != null }

    val scrollState = rememberScrollState()

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
                    GlideImage(
                        imageModel = "https://image.tmdb.org/t/p/original" + (media?.backdropPath ?: String),
                        contentDescription = media?.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color(0x88000000)
                                    )
                                )
                            ),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Text(
                            text = media?.title ?: "Titre media",
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                    }
                }

                Spacer(Modifier.height(30.dp))

                media?.let {
                    Text(
                        text = it.overview ?: "Pas de description disponible",
                        textAlign = TextAlign.Start,
                        fontSize = 12.sp,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    )

                    Spacer(Modifier.height(30.dp))

                    // Affichage des acteurs
                    if (filteredArtists.isNotEmpty()) {
                        Text(
                            text = "Casting",
                            fontSize = 16.sp,
                            color = Color.LightGray
                        )

                        Spacer(Modifier.height(30.dp))

                        Box(
                            modifier = Modifier
                                .padding(0.dp) // Espacement pour séparer les images
                                .fillMaxWidth()
                        ) {
                            val pagerState = rememberPagerState()

                            HorizontalPager(
                                count = filteredArtists.size,
                                state = pagerState,
                                itemSpacing = 0.dp,
                                contentPadding = PaddingValues(end = 300.dp),
                                modifier = Modifier
                                    .heightIn(max = 180.dp)
                                    .fillMaxWidth()
                            ) { page ->
                                val artist = filteredArtists[page]

                                Box(
                                    modifier = Modifier
                                        .height(100.dp)
                                        .width(69.dp)
                                ) {
                                    GlideImage(
                                        imageModel = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2" + artist.profilePath,
                                        contentDescription = artist.name,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clickable {
                                                if (navHostController != null) {
                                                    navHostController.navigate("ArtistPageScreen/${artist.id}")
                                                }
                                            },
                                        contentScale = ContentScale.Crop
                                    )

                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(
                                                brush = Brush.verticalGradient(
                                                    colors = listOf(
                                                        Color.Transparent,
                                                        Color(0x88000000)
                                                    )
                                                )
                                            ),
                                        contentAlignment = Alignment.BottomCenter
                                    ) {
                                        Text(
                                            text = artist.name ?: "Nom non disponible",
                                            fontSize = 10.sp,
                                            lineHeight = 10.sp,
                                            color = Color.White,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(3.dp)
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        Text(
                            text = "Aucun acteur disponible",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
