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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.pixhubandroid.ui.theme.PixhubAndroidTheme
import com.example.pixhubandroid.viewmodel.ArtistPageViewModel
import com.example.pixhubandroid.viewmodel.CalendarViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArtistPageScreenPreview() {
    PixhubAndroidTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val homeViewModel: CalendarViewModel = viewModel()
            val artistPageViewModel : ArtistPageViewModel = viewModel()
            val navHostController = rememberNavController()

            ArtistPageScreen(
                artistId = 0,
                calendarViewModel = homeViewModel,
                artistPageViewModel = artistPageViewModel,
                navHostController = navHostController
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class, ExperimentalPagerApi::class)
@Composable
fun ArtistPageScreen(
    artistId: Int,
    calendarViewModel: CalendarViewModel,
    artistPageViewModel: ArtistPageViewModel,
    navHostController: NavHostController? = null,
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    // Charger les détails de l'artiste et ses films
    LaunchedEffect(artistId) {
        coroutineScope.launch {
            artistPageViewModel.loadArtistDetails(artistId)
            artistPageViewModel.loadArtistMovies(artistId)
        }
    }

    // Obtenez les détails de l'artiste depuis le ViewModel
    val artist by artistPageViewModel.artistsList.firstOrNull { it.id == artistId }?.let { mutableStateOf(it) }
        ?: mutableStateOf(null)
    val media by remember { mutableStateOf(artistPageViewModel.mediaList) }
    val filteredMedias = media.filter { it.title != null && it.posterPath != null }

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
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color(0xFF1E2535))
                    .padding(10.dp)
                    .verticalScroll(scrollState)
            ) {
                Spacer(Modifier.height(30.dp))

                Text(
                    text = artist?.name ?: "Nom artiste",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                Spacer(Modifier.height(30.dp))

                GlideImage(
                    imageModel = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2" + (artist?.profilePath ?: ""),
                    contentDescription = artist?.name,
                    modifier = Modifier
                        .height(150.dp)
                        .width(110.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(Modifier.height(30.dp))

                val biographyText = if (artist?.biography.isNullOrBlank()) "Biographie indisponible" else artist?.biography

                Text(
                    text = biographyText ?: "Biographie indisponible",
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(10.dp)
                )

                Spacer(Modifier.height(30.dp))

                // Affichage des films
                if (filteredMedias.isNotEmpty()) {
                    Text(
                        text = "Filmographie",
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
                            count = filteredMedias.size,
                            state = pagerState,
                            itemSpacing = 0.dp,
                            contentPadding = PaddingValues(end = 300.dp),
                            modifier = Modifier
                                .heightIn(max = 180.dp)
                                .fillMaxWidth()
                        ) { page ->
                            val mediaItem = filteredMedias[page]

                            Box(
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(69.dp)
                            ) {
                                GlideImage(
                                    imageModel = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2" + mediaItem.posterPath,
                                    contentDescription = mediaItem.title,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            if (navHostController != null) {
                                                navHostController.navigate("MediaPageScreen/${mediaItem.id}")
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
                                        text = mediaItem.title ?: "Nom non disponible",
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
                        text = "Aucun film disponible",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}
