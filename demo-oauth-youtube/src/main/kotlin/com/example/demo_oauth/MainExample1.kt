package com.example.demo_oauth

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.model.ChannelListResponse
import java.io.InputStream
import java.io.InputStreamReader

class MainExample1 {
    companion object{
        const val CLIENT_SECRET = "/ignore/client_secret.json"
        val SCOPES = listOf("https://www.googleapis.com/auth/youtube.readonly")
        val APPLICATION_NAME = "API Code Sample"
        val JSON_FACTORY = JacksonFactory.getDefaultInstance()
    }

    fun authorize(httpTransport: NetHttpTransport) : Credential{
        val inputStream: InputStream = MainExample1.javaClass.getResourceAsStream(CLIENT_SECRET)
        val clientSecrets: GoogleClientSecrets = GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(inputStream))

        val flow: GoogleAuthorizationCodeFlow =
            GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                .build()

        val credential: Credential = AuthorizationCodeInstalledApp(flow, LocalServerReceiver()).authorize("user")
        return credential
    }

    fun getService(): YouTube {
        val httpTransport: NetHttpTransport = GoogleNetHttpTransport.newTrustedTransport()
        val credential: Credential = authorize(httpTransport)
        return YouTube.Builder(httpTransport, JSON_FACTORY, credential)
            .setApplicationName(APPLICATION_NAME)
            .build()
    }
}

fun main(args: Array<String>){
    val main = MainExample1()
    val youtubeService : YouTube = main.getService()
    val request : YouTube.Channels.List = youtubeService.channels()
        .list("snippet,contentDetails,statistics")

    val response: ChannelListResponse = request.setId("UC_x5XG1OV2P6uZZ5FSM9Ttw").execute()
    println(response)
}