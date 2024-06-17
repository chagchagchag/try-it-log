package io.chagchagchag.youtube.playlist_helper.api_client.domain.auth.dto

data class GoogleTokenResponse (
    val access_token: String,
    val expires_in: String,
    val scope: String,
    val token_type: String,
    val id_token: String,
){
}