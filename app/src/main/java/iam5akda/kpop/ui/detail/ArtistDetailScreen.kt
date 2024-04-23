package iam5akda.kpop.ui.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import iam5akda.kpop.R
import iam5akda.kpop.model.ArtistDetail
import iam5akda.kpop.ui.theme.KPopTheme

@Composable
fun ArtistDetailScreen(
    viewModel: ArtistDetailViewModel = hiltViewModel(),
    artistName: String,
    onBackClick: () -> Unit,
    onSpotifyClick: (url: String) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.requestArtistDetail(artistName)
    }
    val uiContent by viewModel.uiContent.collectAsStateWithLifecycle()
    uiContent?.let {
        ArtistDetailLayout(
            artistDetail = it,
            onBackClick = onBackClick,
            onSpotifyClick = onSpotifyClick
        )
    }
}

@Composable
private fun ArtistDetailLayout(
    artistDetail: ArtistDetail,
    onBackClick: () -> Unit,
    onSpotifyClick: (url: String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.Yellow)
                    .padding(horizontal = 16.dp)
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable { onBackClick() },
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.DarkGray)
                )
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    text = artistDetail.displayName,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = Color.LightGray)
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = artistDetail.imageId),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.ic_member),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Gray)
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 8.dp),
                    text = "${artistDetail.numberOfMembers}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f),
                    text = artistDetail.recordLabel,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    color = Color.Gray
                )
            }
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                text = artistDetail.detail,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Button(
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
                border = BorderStroke(2.dp, Color.DarkGray),
                onClick = { onSpotifyClick(artistDetail.spotifyUrl) }
            ) {
                Text(
                    text = "Listen on Spotify",
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(device = Devices.PIXEL_2)
@Composable
private fun ArtistDetailLayoutPreview() {
    KPopTheme {
        val mock = ArtistDetail(
            name = "",
            displayName = "NewJeans (뉴진스)",
            imageId = R.drawable.img_newjeans,
            recordLabel = "ADOR, Geffen",
            numberOfMembers = 5,
            detail = "뉴진스 consists of five members: Minji, Hanni, Danielle, Haerin, and Hyein. They officially debuted on July 22, 2022.",
            spotifyUrl = ""
        )
        ArtistDetailLayout(
            artistDetail = mock,
            onBackClick = {},
            onSpotifyClick = {}
        )
    }
}