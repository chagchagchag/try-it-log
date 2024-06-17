package io.chagchagchag.youtube.playlist_helper.api_client.domain.auth.dto

data class GoogleAuthRequest (
    val code: String,
    val client_id: String,
    val client_secret: String,
    val redirect_uri: String,
    val grant_type: String
){
}