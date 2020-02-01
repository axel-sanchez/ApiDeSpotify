package com.example.apidespotify

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("/api/token?grant_type=client_credentials&client_id=ad9797f1312949b59f76faaf9a709a6d&client_secret=75822014ca2943e8994978072df4086c")
    fun getToken(): Call<Token>

    @Headers("Content-Type: application/x-www-form-urlencoded", "Authorization: Bearer BQCQrUkf6AZkS5LpQLByMAPafOnSFu00HS0Vaz4xK4qUWhJJ-12up1erBJSmdJQ0EMdrSQCoERao6fit3q0")
    @GET("search?q=no hay nadie mas&type=track")
    fun getSong(): Call<Song>

}