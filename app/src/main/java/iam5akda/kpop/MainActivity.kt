package iam5akda.kpop

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import iam5akda.kpop.ui.detail.ArtistDetailScreen
import iam5akda.kpop.ui.home.HomeScreen
import iam5akda.kpop.ui.settings.SettingsScreen
import iam5akda.kpop.ui.theme.KPopTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KPopTheme(darkTheme = false, dynamicColor = false) {
                KPopNavHost(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun KPopNavHost(modifier: Modifier) {

    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "home"
    ) {
        composable(
            route = "home"
        ) {
            HomeScreen(
                onArtistClick = { artistName ->
                    navController.navigate("artistDetail/$artistName")
                },
                onSettingsClick = {
                    navController.navigate("settings")
                }
            )
        }

        composable(
            route = "artistDetail/{artistName}",
            arguments = listOf(
                navArgument("artistName") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) {
            val context = LocalContext.current

            ArtistDetailScreen(
                artistName = it.arguments?.getString("artistName") ?: "",
                onBackClick = {
                    navController.popBackStack()
                },
                onSpotifyClick = { url ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
            )
        }

        composable(
            route = "settings"
        ) {
            SettingsScreen(
                onCloseClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}