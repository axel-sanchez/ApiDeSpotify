package com.example.apidespotify

import com.example.apidespotify.data.models.FeaturedPlaylist
import com.example.apidespotify.data.models.Root
import com.example.apidespotify.data.models.Token
import com.example.apidespotify.data.models.Track
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("/api/token?grant_type=client_credentials&client_id=ad9797f1312949b59f76faaf9a709a6d&client_secret=75822014ca2943e8994978072df4086c")
    fun getToken(): Call<Token>

    @GET("search")
    fun search(@Query("q") name: String, @Query("type") type: String, @Query("market") market: String, @Header("Content-Type") contentType: String, @Header("Authorization") token: String): Call<Root>

    @GET("tracks/{id}?market=us")
    fun getSong(@Path("id") id: String, @Header("Content-Type") contentType: String, @Header("Authorization") token: String): Call<Track>

    @GET("browse/featured-playlists")
    fun getFeaturedPlaylists(@Header("Authorization") token: String): Call<FeaturedPlaylist>

}