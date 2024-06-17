package io.chagchagchag.youtube.playlist_helper.api_client.domain.auth.client

import feign.Headers
import feign.RequestLine
import io.chagchagchag.youtube.playlist_helper.api_client.domain.auth.dto.GoogleAuthRequest
import io.chagchagchag.youtube.playlist_helper.api_client.domain.auth.dto.GoogleTokenResponse
import reactor.core.publisher.Mono

interface AuthClient {
  @RequestLine("POST /token")
  @Headers("Content-Type: application/json")
  fun requestToken(googleAuthRequest: GoogleAuthRequest): Mono<GoogleTokenResponse>

}