package io.chagchagchag.youtube.playlist_helper.api_client.domain.auth.dto

data class GoogleAuthResponse(
    val sub: String,
    val name: String,
    val givenName: String,
    val familyName: String?,
    val picture: String,
    val email: String,
    val emailVerified: Boolean,
    val local: String,
)
