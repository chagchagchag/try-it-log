package io.chagchagchag.youtube.playlist_helper.api_client.domain.auth.application

import io.chagchagchag.youtube.playlist_helper.api_client.domain.auth.client.AuthClient
import io.chagchagchag.youtube.playlist_helper.api_client.domain.auth.dto.GoogleAuthRequest
import io.chagchagchag.youtube.playlist_helper.api_client.domain.auth.dto.GoogleTokenResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AuthApplicationService (
    @Value("\${google.account.client_id}") val clientId: String,
    @Value("\${google.account.client_secret}") val clientSecret: String,
    @Value("\${google.account.redirect_uri}") val redirectUri: String,
    @Value("\${google.account.grant_type}") val grantType: String,
    val authClient: AuthClient
){

  fun requestAccessToken(code: String): Mono<GoogleTokenResponse> {
    val request = GoogleAuthRequest(
        code = code,
        client_id = clientId,
        client_secret = clientSecret,
        redirect_uri = redirectUri,
        grant_type = grantType
    )
    return authClient.requestToken(request)
  }

}