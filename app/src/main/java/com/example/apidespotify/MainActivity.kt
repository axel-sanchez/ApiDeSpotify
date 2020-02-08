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
import android.media.AudioManager


const val BASE_URL = "https://api.spotify.com/v1/"

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

    private fun getToken() {
        //Recibimos el token de spotify
        serviceToken.getToken().enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                if (response.isSuccessful) {
                    println("body token: ${response.body()!!.getAccessToken()}")
                    bearer = response.body()!!.getAccessToken()
                    search()
                } else {
                    println("response token: $response")
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun search() {
        //Recibimos la cancion de spotify
        try {
            service.search(
                "Someone you loved",
                "track",
                "us",
                "application/x-www-form-urlencoded",
                "Bearer $bearer"
            ).enqueue(object : Callback<Root> {
                override fun onResponse(call: Call<Root>, response: Response<Root>) {
                    if (response.isSuccessful) {
                        var song = response.body()
                        var pos = 0

                        var url = song!!.getTracks().getItems()[pos].getPreview_url()
                        var urlImage = song.getTracks().getItems()[pos].getAlbum().getImages()[0].getUrl()

                        while(url.isNullOrEmpty() || urlImage.isNullOrEmpty()){
                            pos++
                            url = song.getTracks().getItems()[pos].getPreview_url()
                            urlImage = song.getTracks().getItems()[pos].getAlbum().getImages()[0].getUrl()
                        }

                        Glide.with(this@MainActivity)
                            .load(urlImage)
                            .into(imagen)

                        //var url = "https://p.scdn.co/mp3-preview/596fa2d78816cba29f4ded436a89ca69483a65a0?cid=ad9797f1312949b59f76faaf9a709a6d"
                        println("el url de la cancion es: $url")
                        MediaPlayer().apply {
                            setAudioStreamType(AudioManager.STREAM_MUSIC)
                            setDataSource(url)
                            prepare() // might take long! (for buffering, etc)
                            start()
                        }

                        //getSong(song!!.tracks.items[0].id)
                    } else {
                        println("error response song: $response")
                    }
                }

                override fun onFailure(call: Call<Root>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun getSong(id: String) {
        service.getSong(id, "application/x-www-form-urlencoded", "Bearer $bearer")
            .enqueue(object : Callback<Track> {
                override fun onResponse(call: Call<Track>, response: Response<Track>) {
                    if (response.isSuccessful) {
                        println("body song: ${response.body()}")
                        var song = response.body()
                        //var url = song!!.tracks.items[0].previewURL
                        var url = song!!.previewURL
                        //var urlImage = song.tracks.items[0].album.images[0].url
                        var urlImage = song.album.images[0].url

                        Glide.with(this@MainActivity)
                            .load(urlImage)
                            .into(imagen)

                        //var url = "https://p.scdn.co/mp3-preview/596fa2d78816cba29f4ded436a89ca69483a65a0?cid=ad9797f1312949b59f76faaf9a709a6d"
                        println("el url de la cancion es: $url")
                        MediaPlayer().apply {
                            setAudioStreamType(AudioManager.STREAM_MUSIC)
                            setDataSource(url)
                            prepare() // might take long! (for buffering, etc)
                            start()
                        }
                    } else {
                        println("error response song: $response")
                    }

                }

                override fun onFailure(call: Call<Track>, t: Throwable) {

                }
            })
    }
}