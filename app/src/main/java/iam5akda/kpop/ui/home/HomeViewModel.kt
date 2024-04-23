package iam5akda.kpop.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import iam5akda.kpop.R
import iam5akda.kpop.model.ArtistHighlight
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private val _uiContent = MutableStateFlow(listOf<ArtistHighlight>())
    val uiContent: StateFlow<List<ArtistHighlight>> = _uiContent

    fun requestArtistList() {
        _uiContent.value = listOf(
            ArtistHighlight(
                name = "키스오브라이프",
                displayName = "Kiss of Life",
                imageId = R.drawable.img_kissoflife
            ),
            ArtistHighlight(
                name = "아일릿",
                displayName = "ILLIT",
                imageId = R.drawable.img_illit
            ),
            ArtistHighlight(
                name = "베이비몬스터",
                displayName = "BABYMONSTER",
                imageId = R.drawable.img_babymonster
            )
        )
    }
}