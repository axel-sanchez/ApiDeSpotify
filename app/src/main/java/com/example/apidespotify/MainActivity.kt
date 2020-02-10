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
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apidespotify.data.models.*
import com.example.apidespotify.ui.view.adapter.PlaylistAdapter
import java.lang.Exception


const val BASE_URL = "https://api.spotify.com/v1/"

@RequiresApi(Build.VERSION_CODES.N)
class MainActivity : AppCompatActivity() {

    private lateinit var serviceToken: ApiService
    private lateinit var service: ApiService

    private var listItems = mutableListOf<Item>()

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

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
                    //search()

                    getFeaturedPlaylists()
                } else {
                    println("response token: $response")
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun getFeaturedPlaylists() {
        try {
            service.getFeaturedPlaylists(
                "Bearer $bearer"
            ).enqueue(object : Callback<FeaturedPlaylist> {
                override fun onResponse(call: Call<FeaturedPlaylist>, response: Response<FeaturedPlaylist>) {
                    if (response.isSuccessful) {
                        var body = response.body()

                        listItems = body!!.getPlaylists().getItems().toMutableList()

                        listItems.removeIf { x -> x.getImages()[0].getUrl().isNullOrEmpty() }

                        setAdapter()
                    } else {
                        println("error response song: $response")
                    }
                }

                override fun onFailure(call: Call<FeaturedPlaylist>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }catch (e: Exception){
            e.printStackTrace()
        }
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
        } catch (e: Exception) {
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

    fun setAdapter() {
        viewAdapter = PlaylistAdapter(listItems) { itemClick(it) }

        viewManager = GridLayoutManager(applicationContext, 2)

        playlists.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
    }

    private fun itemClick(playlist: Item){
        Toast.makeText(applicationContext, "Presionó la lista ${playlist.getName()}", Toast.LENGTH_SHORT).show()
    }
}