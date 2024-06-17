package io.chagchagchag.youtube.playlist_helper.api_client.domain.jsonplaceholder.dto

data class Post(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String
) {
  companion object {
    fun empty(): Post {
      return Post(
          id = -1,
          userId = -1,
          title = "",
          body = ""
      )
    }
  }
}
