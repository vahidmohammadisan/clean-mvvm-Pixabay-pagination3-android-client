package info.vahidmohammadi.data.api

import info.vahidmohammadi.domain.model.ImageList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {
    @GET("api/")
    suspend fun getRemoteImages(
        @Query("key") apiKey: String,
        @Query("q") keyword: String,
        @Query("page") page: Int = 1,
    ): Response<ImageList>
}