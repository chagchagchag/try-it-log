package io.chagchagchag.youtube.playlist_helper.api_client.config.feign_clients

import com.google.gson.GsonBuilder
import feign.Logger
import feign.RequestInterceptor
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder
import feign.reactive.ReactorDecoder
import feign.reactive.ReactorFeign
import io.chagchagchag.youtube.playlist_helper.api_client.domain.auth.client.AuthClient
import io.chagchagchag.youtube.playlist_helper.api_client.openfeign.CustomErrorDecoder
import org.apache.http.entity.ContentType
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthClientConfig (
    @Value("\${feign-clients.google.step3.url}") val url: String
){
  @Bean
  fun authClient(): AuthClient{
    val gson = GsonBuilder()
            .setLenient()
            .create()

    return ReactorFeign.builder()
            .logLevel(Logger.Level.FULL)
            .encoder(GsonEncoder(gson))
            .decoder(ReactorDecoder(GsonDecoder(gson)))
            .errorDecoder(CustomErrorDecoder())
            .requestInterceptor(RequestInterceptor {
              it.header("Accept", ContentType.APPLICATION_JSON.mimeType)
            })
            .target(AuthClient::class.java, url)
  }
}