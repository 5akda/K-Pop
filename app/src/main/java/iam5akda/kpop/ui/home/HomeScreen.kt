package iam5akda.kpop.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import iam5akda.kpop.R
import iam5akda.kpop.model.ArtistHighlight
import iam5akda.kpop.ui.theme.KPopTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onArtistClick: (artistName: String) -> Unit,
    onSettingsClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.requestArtistList()
    }
    val uiContent by viewModel.uiContent.collectAsStateWithLifecycle()
    HomeLayout(
        artistList = uiContent,
        onArtistClick = onArtistClick,
        onSettingsClick = onSettingsClick
    )
}

@Composable
private fun HomeLayout(
    artistList: List<ArtistHighlight>,
    onArtistClick: (artistName: String) -> Unit,
    onSettingsClick: () -> Unit
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
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    text = "Welcome to K-Pop!",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable { onSettingsClick() },
                    painter = painterResource(id = R.drawable.ic_setting),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.DarkGray)
                )
            }
        }
    ) { innerPadding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = Color.LightGray)
                .verticalScroll(scrollState)
                .padding(vertical = 12.dp)
        ) {
            artistList.forEach {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(4.dp, Color.DarkGray, RoundedCornerShape(12.dp))
                        .clickable { onArtistClick(it.name) }
                        .background(Color.DarkGray)
                        .height(240.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        painter = painterResource(id = it.imageId),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 12.dp, bottom = 8.dp),
                        text = it.displayName,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Image(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp),
                        painter = painterResource(id = R.drawable.ic_arrow_forward),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Gray)
                    )
                }
            }
        }
    }
}

@Preview(device = Devices.PIXEL_2)
@Composable
private fun HomeLayoutPreview() {
    KPopTheme {
        HomeLayout(
            artistList = listOf(
                ArtistHighlight("", "Kiss of Life", R.drawable.img_kissoflife),
                ArtistHighlight("", "ILLIT", R.drawable.img_illit),
                ArtistHighlight("", "BABYMONSTER", R.drawable.img_babymonster)
            ),
            onArtistClick = {},
            onSettingsClick = {}
        )
    }
}
