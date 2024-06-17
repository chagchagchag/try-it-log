package io.chagchagchag.youtube.playlist_helper.api_client.domain.playlist.rest

import io.chagchagchag.youtube.playlist_helper.api_client.domain.playlist.application.PlaylistApplicationService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaylistController (
  val playlistApplicationService: PlaylistApplicationService
){
  val logger: Logger = LoggerFactory.getLogger(PlaylistController::class.java)

  @GetMapping("/youtube/test")
  fun youtubeTest(): String{
    val response = playlistApplicationService.requestListPlaylist()
    logger.info("{}", response)
    return "OK"
  }

}