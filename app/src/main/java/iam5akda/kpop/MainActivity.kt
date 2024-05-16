package iam5akda.kpop

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint
import iam5akda.kpop.ui.detail.ArtistDetailScreen
import iam5akda.kpop.ui.home.HomeScreen
import iam5akda.kpop.ui.settings.SettingsScreen
import iam5akda.kpop.ui.theme.KPopTheme
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KPopTheme(darkTheme = false, dynamicColor = false) {
                KPopNavHost()
            }
        }
    }
}

class Route {
    @Serializable
    object Home

    @Serializable
    data class ArtistDetail(val name: String)

    @Serializable
    object Settings
}

@Composable
fun KPopNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Home
    ) {
        composable<Route.Home> {
            HomeScreen(
                onArtistClick = { artistName: String ->
                    navController.navigate(
                        Route.ArtistDetail(name = artistName)
                    )
                },
                onSettingsClick = {
                    navController.navigate(Route.Settings)
                }
            )
        }
        composable<Route.ArtistDetail> {
            val context = LocalContext.current
            val args = it.toRoute<Route.ArtistDetail>()
            ArtistDetailScreen(
                artistName = args.name,
                onBackClick = {
                    navController.popBackStack()
                },
                onSpotifyClick = { url ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
            )
        }
        composable<Route.Settings> {
            SettingsScreen(
                onCloseClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}