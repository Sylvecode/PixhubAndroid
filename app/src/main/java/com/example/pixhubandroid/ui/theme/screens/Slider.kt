package com.example.pixhubandroid.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.pixhubandroid.R
import com.example.pixhubandroid.ui.theme.Routes
import com.example.pixhubandroid.viewmodel.CalendarViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalPagerApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun Slider(calendarViewModel: CalendarViewModel, navHostController: NavHostController) {
    LaunchedEffect(key1 = "") {
        calendarViewModel.loadData() // Charge les données nécessaires
    }

    val pagerState = rememberPagerState(initialPage = 0)

    HorizontalPager(
        count = calendarViewModel.list.size,
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->

        val media = calendarViewModel.list[page]
        val formattedDate = formatDateToFrench(media.releaseDate)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    navHostController.navigate("MediaPageScreen/${media.id}")
                }
        ) {

            Text(
                text = formattedDate,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start)
                    .padding(1.dp),
                color = Color.LightGray,
                fontSize = 15.sp
            )
/*
            Text(
                text = "Jeux vidéos",
                fontSize = 10.sp,
                color = Color.LightGray,
                modifier = Modifier
                    .wrapContentWidth(Alignment.End)
                .clickable {
                    navHostController.navigate("GamePageScreen")
                }
            )

*/

            Spacer(Modifier.height(12.dp))

            calendarViewModel.list[page].title?.let {
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




            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp) // Espacement pour séparer les images
                // Application de RoundedCornerShape au conteneur Box
            ) {


                Spacer(Modifier.height(20.dp))

                GlideImage(
                    model = "https://image.tmdb.org/t/p/w500/" + calendarViewModel.list[page].posterPath,
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
                        .clickable {
                            navHostController.navigate("MediaPageScreen/${media.id}")
                        }

                )

            }
        }
    }
}


fun formatDateToFrench(dateString: String?): String {
    if (dateString == null) return ""
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH)
    return try {
        val date = inputFormat.parse(dateString)
        outputFormat.format(date)
    } catch (e: Exception) {
        ""
    }
}