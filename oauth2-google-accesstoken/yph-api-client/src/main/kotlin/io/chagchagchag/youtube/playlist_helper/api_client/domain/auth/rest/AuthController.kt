package io.chagchagchag.youtube.playlist_helper.api_client.domain.auth.rest

import io.chagchagchag.youtube.playlist_helper.api_client.domain.auth.application.AuthApplicationService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class AuthController (
    val authApplicationService: AuthApplicationService
){
  val logger : Logger = LoggerFactory.getLogger(this.javaClass)

  @GetMapping("/welcome")
  fun welcome(
          @RequestParam("code") code: String,
          @RequestParam("scope") scope: String,
  ): Mono<String>{
    return authApplicationService.requestAccessToken(code)
            .map { it.access_token }
  }

  @GetMapping("/auth")
  fun getAuth(
      @RequestHeader("token") token : Optional<String>
  ): Mono<String> {
    return Mono.empty()
  }
}