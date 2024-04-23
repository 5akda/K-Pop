package iam5akda.kpop.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import iam5akda.kpop.R

@Composable
fun SettingsScreen(onCloseClick: () -> Unit) {
    SettingsLayout(onCloseClick = onCloseClick)
}

@Composable
private fun SettingsLayout(onCloseClick: () -> Unit) {
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
                    text = "Settings",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable { onCloseClick() },
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.DarkGray)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.LightGray)
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            listOf("1st", "2nd", "3rd", "4th", "5th").forEach {
                Row(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically)
                            .weight(1f),
                        text = "Stan $it Gen Girl Groups",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Switch(
                        checked = it == "5th",
                        onCheckedChange = {}
                    )
                }
            }
        }
    }
}

@Preview(device = Devices.PIXEL_2)
@Composable
private fun SettingsLayoutPreview() {
    SettingsLayout(onCloseClick = {})
}