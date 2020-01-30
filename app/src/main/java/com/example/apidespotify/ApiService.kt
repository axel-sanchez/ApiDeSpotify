package com.example.apidespotify

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("/api/token?grant_type=client_credentials&client_id=ad9797f1312949b59f76faaf9a709a6d&client_secret=75822014ca2943e8994978072df4086c")
    fun getToken(): Call<Token>

    @Headers("Content-Type: application/x-www-form-urlencoded", "Authorization: Bearer BQC1LZRyXO3VyW2K4Vv3VtdH0i5TUdD4FrK_9bYsVcH0oQApO_yMeI06gT1ypvyxch65CkcTfa1Cv8dKECI")
    @GET("search?q=no hay nadie mas&type=track")
    fun getSong(): Call<Song>

}