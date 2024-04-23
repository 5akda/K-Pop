package iam5akda.kpop.model

data class ArtistDetail(
    val name: String,
    val displayName: String,
    val imageId: Int,
    val recordLabel: String,
    val numberOfMembers: Int,
    val detail: String,
    val spotifyUrl: String
)