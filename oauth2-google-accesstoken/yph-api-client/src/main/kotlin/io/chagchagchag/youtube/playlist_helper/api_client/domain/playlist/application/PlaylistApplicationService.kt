package io.chagchagchag.youtube.playlist_helper.api_client.domain.playlist.application

import com.google.api.services.youtube.model.ChannelListResponse
import io.chagchagchag.youtube.playlist_helper.api_client.youtube.YoutubePlaylistHelper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PlaylistApplicationService (
    val playlistHelper: YoutubePlaylistHelper
){
  val logger: Logger = LoggerFactory.getLogger(PlaylistApplicationService::class.java)


  //// REFACTORING
  // part 는 요청의 종류에 따라달라지기에 Part 의 요청에 따라 동작의 종류가 달라질 수 있도록 interface 로 분리하거나 함수를 여러가지를 만들것
  fun requestListPlaylist() : ChannelListResponse{
    val youtube = playlistHelper.playlistSupplier().get()

    val part: String = "snippet,contentDetails,statistics"

    val channelListResponse = playlistHelper.requestPlaylist(part, youtube)

    logger.info("{}", channelListResponse)

    return channelListResponse
  }
}