package io.chagchagchag.youtube.playlist_helper.api_client.domain.jsonplaceholder.client

import feign.Param
import feign.RequestLine
import io.chagchagchag.youtube.playlist_helper.api_client.domain.jsonplaceholder.dto.Post
import reactor.core.publisher.Mono

interface PostClient {
  @RequestLine("GET /posts/{id}")
  fun getPostById(@Param("id") id: Int): Mono<Post>
}