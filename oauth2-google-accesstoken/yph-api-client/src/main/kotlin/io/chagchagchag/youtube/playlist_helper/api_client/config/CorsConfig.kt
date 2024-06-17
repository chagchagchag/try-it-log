package io.chagchagchag.youtube.playlist_helper.api_client.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
class CorsConfig() : WebFluxConfigurer{
  override fun addCorsMappings(registry: CorsRegistry) {
    registry.addMapping("/**")
            .allowedOriginPatterns("*")
            .allowedMethods("*")
  }
}