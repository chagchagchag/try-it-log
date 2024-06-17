package io.chagchagchag.youtube.playlist_helper.api_client.youtube

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.model.ChannelListResponse
import io.chagchagchag.youtube.playlist_helper.api_client.domain.playlist.environment.PlaylistEnvironment
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.InputStream
import java.io.InputStreamReader
import java.util.function.Supplier

@Component
class YoutubePlaylistHelper (
    @Value("\${spring.application.name}") val APPLICATION_NAME: String
){
  fun playlistSupplier(): Supplier<YouTube> = Supplier {
    val inputStream: InputStream = this.javaClass.getResourceAsStream(PlaylistEnvironment.CLIENT_SECRET)
    val clientSecrets: GoogleClientSecrets = GoogleClientSecrets.load(PlaylistEnvironment.JSON_FACTORY, InputStreamReader(inputStream))
    val httpTransport: NetHttpTransport = GoogleNetHttpTransport.newTrustedTransport()

    val flow: GoogleAuthorizationCodeFlow =
            GoogleAuthorizationCodeFlow.Builder(httpTransport, PlaylistEnvironment.JSON_FACTORY, clientSecrets, PlaylistEnvironment.SCOPES)
                    .build()

    val credential: Credential = AuthorizationCodeInstalledApp(flow, LocalServerReceiver()).authorize("user")

    val youtube = YouTube.Builder(httpTransport, PlaylistEnvironment.JSON_FACTORY, credential)
            .setApplicationName(APPLICATION_NAME)
            .build()
    youtube
  }

  fun requestPlaylist(part: String, youtube: YouTube): ChannelListResponse {
    val req : YouTube.Channels.List = youtube.channels()
            .list(part)

    // unique id 만들어내는 로직 추가 할 것
    
    return req.setId("UC_x5XG1OV2P6uZZ5FSM9Ttw").execute()
  }

  fun requestPlaylistFunction(): (String) -> ChannelListResponse = { part: String ->
    val youtube = playlistSupplier().get()

    val req : YouTube.Channels.List = youtube.channels()
            .list(part)

    val response = req.setId("UC_x5XG1OV2P6uZZ5FSM9Ttw").execute()
    response
  }
}