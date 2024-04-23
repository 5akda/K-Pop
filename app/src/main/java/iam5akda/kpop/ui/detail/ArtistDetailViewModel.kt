package iam5akda.kpop.ui.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import iam5akda.kpop.R
import iam5akda.kpop.model.ArtistDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(): ViewModel() {

    private val _uiContent = MutableStateFlow<ArtistDetail?>(null)
    val uiContent: StateFlow<ArtistDetail?> = _uiContent

    fun requestArtistDetail(name: String) {
        val allArtist = listOf(
            ArtistDetail(
                name = "키스오브라이프",
                displayName = "Kiss of Life",
                imageId = R.drawable.img_kissoflife,
                recordLabel = "S2 Entertainment",
                numberOfMembers = 4,
                detail = "키스오브라이프 consists of four members: Julie, Natty, Belle, and Haneul. They officially debuted on July 5, 2023.",
                spotifyUrl = "https://open.spotify.com/artist/4TEK9tIkcoxib4GxT3O4ky?si=Ae9d96w8QsWNsUaOqwlD1g"
            ),
            ArtistDetail(
                name = "아일릿",
                displayName = "ILLIT",
                imageId = R.drawable.img_illit,
                recordLabel = "BELIFT LAB",
                numberOfMembers = 5,
                detail = "아일릿 consists of five members: Yunah, Minju, Moka, Wonhee, and Iroha. They officially debuted on March 25, 2024.",
                spotifyUrl = "https://open.spotify.com/artist/36cgvBn0aadzOijnjjwqMN?si=0tyRXo6wR0emt7J4SCMiDw"
            ),
            ArtistDetail(
                name = "베이비몬스터",
                displayName = "BABYMONSTER",
                imageId = R.drawable.img_babymonster,
                recordLabel = "YG Entertainment",
                numberOfMembers = 7,
                detail = "베이비몬스터 consists of seven members: Ruka, Pharita, Asa, Ahyeon, Rami, Rora, and Chiquita. They officially debuted on November 27, 2023.",
                spotifyUrl = "https://open.spotify.com/artist/1SIocsqdEefUTE6XKGUiVS?si=eTmber57RbSLKzhGixQxjQ"
            )
        )
        _uiContent.value = allArtist.find { it.name == name }
    }
}