package iam5akda.kpop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import iam5akda.kpop.ui.theme.KPopTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KPopTheme(darkTheme = false, dynamicColor = false) {
                // implement navigation here
            }
        }
    }
}