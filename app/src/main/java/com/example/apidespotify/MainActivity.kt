package com.example.apidespotify

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.media.AudioManager
import android.net.Uri
import retrofit2.http.Headers


val BASE_URL = "https://api.spotify.com/v1/"

class MainActivity : AppCompatActivity() {

    lateinit var serviceToken: ApiService
    lateinit var service: ApiService

    var bearer = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitToken: Retrofit = Retrofit.Builder()
            .baseUrl("https://accounts.spotify.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        serviceToken = retrofitToken.create<ApiService>(ApiService::class.java)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create<ApiService>(ApiService::class.java)

        getToken()
    }

    fun getToken() {
        //Recibimos el token de spotify
        serviceToken.getToken().enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                if(response.isSuccessful){
                    println("body token: ${response.body()!!.getAccessToken()}")
                    bearer = response.body()!!.getAccessToken()
                    getSong()
                } else{
                    println("response token: $response")
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    //https://open.spotify.com/track/7kh95COsNZjnTmc6aF76n7

    fun getSong(){
        //Recibimos la cancion de spotify
        println("el bearer es $bearer")
        try {
            service.getSong().enqueue(object: Callback<Song> {
                override fun onResponse(call: Call<Song>, response: Response<Song>) {
                    if(response.isSuccessful){
                        println("body song: ${response.body()}")
                        var song = response.body()
                        Glide.with(this@MainActivity)
                            .load(song?.tracks!!.items[0].album.images[0].url)
                            .into(imagen)
                        try {
                            var url = "https://p.scdn.co/mp3-preview/596fa2d78816cba29f4ded436a89ca69483a65a0?cid=ad9797f1312949b59f76faaf9a709a6d"
                            val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
                                setAudioStreamType(AudioManager.STREAM_MUSIC)
                                setDataSource(url)
                                prepare() // might take long! (for buffering, etc)
                                start()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    } else{
                        println("error response song: $response")
                    }
                }

                override fun onFailure(call: Call<Song>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        } catch (e: java.lang.Exception){
            e.printStackTrace()
        }
    }
}