// To parse the JSON, install jackson-module-kotlin and do:
//
//   val song = Song.fromJson(jsonString)

package com.example.apidespotify

import java.util.*

class External_urls {
    private var spotify: String = ""

    fun setSpotify(spotify: String){
        this.spotify = spotify
    }
    fun getSpotify(): String {
        return this.spotify
    }
}

class Artists {
    private lateinit var external_urls: External_urls

    private var href: String = ""

    private var id: String = ""

    private var name: String = ""

    private var type: String = ""

    private var uri: String = ""

    fun setExternal_urls(external_urls: External_urls) {
        this.external_urls = external_urls
    }
    fun getExternal_urls(): External_urls {
        return this.external_urls
    }
    fun setHref(href: String) {
        this.href = href
    }
    fun getHref(): String {
        return this.href
    }
    fun setId(id: String) {
        this.id = id
    }
    fun getId(): String {
        return this.id
    }
    fun setName(name: String) {
        this.name = name
    }
    fun getName(): String {
        return this.name
    }
    fun setType(type: String) {
        this.type = type
    }
    fun getType(): String {
        return this.type
    }
    fun setUri(uri: String) {
        this.uri = uri
    }
    fun getUri(): String {
        return this.uri
    }
}

class Images {
    private var height: Int = 0

    private var url: String = ""

    private var width: Int = 0

    fun setHeight(height: Int) {
        this.height = height
    }
    fun getHeight(): Int {
        return this.height
    }
    fun setUrl(url: String) {
        this.url = url
    }
    fun getUrl(): String {
        return this.url
    }
    fun setWidth(width: Int) {
        this.width = width
    }
    fun getWidth(): Int {
        return this.width
    }
}


class Album {
    private var album_type: String = ""

    private lateinit var artists: List<Artists>

    private lateinit var available_markets: List<String>

    private lateinit var external_urls: External_urls

    private var href: String = ""

    private var id: String = ""

    private lateinit var images: List<Images>

    private var  name: String = ""

    private lateinit var release_date: Date

    private var release_date_precision: String = ""

    private var total_tracks: Int = 0

    private var type: String = ""

    private var uri: String = ""

    fun setAlbum_type(album_type: String) {
        this.album_type = album_type
    }
    fun getAlbum_type(): String {
        return this.album_type
    }
    fun setArtists(artists: List<Artists>) {
        this.artists = artists
    }
    fun getArtists(): List<Artists> {
        return this.artists
    }
    fun setAvailable_markets(available_markets: List<String>) {
        this.available_markets = available_markets
    }
    fun getAvailable_markets(): List<String> {
        return this.available_markets
    }
    fun setExternal_urls(external_urls: External_urls) {
        this.external_urls = external_urls
    }
    fun getExternal_urls(): External_urls {
        return this.external_urls
    }
    fun setHref(href: String) {
        this.href = href
    }
    fun getHref(): String {
        return this.href
    }
    fun setId(id: String) {
        this.id = id
    }
    fun getId(): String {
        return this.id
    }
    fun setImages(images: List<Images>) {
        this.images = images
    }
    fun getImages(): List<Images> {
        return this.images
    }
    fun setName(name: String) {
        this.name = name
    }
    fun getName(): String {
        return this.name
    }
    fun setRelease_date(release_date: Date) {
        this.release_date = release_date
    }
    fun getRelease_date(): Date {
        return this.release_date
    }
    fun setRelease_date_precision(release_date_precision: String) {
        this.release_date_precision = release_date_precision
    }
    fun getRelease_date_precision(): String {
        return this.release_date_precision
    }
    fun setTotal_tracks(total_tracks: Int) {
        this.total_tracks = total_tracks
    }
    fun getTotal_tracks(): Int {
        return this.total_tracks
    }
    fun setType(type: String) {
        this.type = type
    }
    fun getType(): String {
        return this.type
    }
    fun setUri(uri: String) {
        this.uri = uri
    }
    fun getUri(): String {
        return this.uri
    }
}


class External_ids {
    private var isrc: String = ""

    fun setIsrc(isrc: String) {
        this.isrc = isrc
    }
    fun getIsrc(): String {
        return this.isrc
    }
}

class Items {
    private lateinit var album: Album

    private lateinit var artists: List<Artists>

    private lateinit var available_markets: List<String>

    private var disc_number: Int = 0

    private var duration_ms: Int = 0

    private var explicit: Boolean = false

    private lateinit var external_ids: External_ids

    private lateinit var external_urls: External_urls

    private var href: String = ""

    private var id: String = ""

    private var is_local: Boolean = false

    private var name: String = ""

    private var popularity: Int = 0

    private var preview_url: String = ""

    private var track_number: Int = 0

    private var type: String = ""

    private var uri: String = ""

    fun setAlbum(album: Album) {
        this.album = album
    }
    fun getAlbum(): Album {
        return this.album
    }
    fun setArtists(artists: List<Artists>) {
        this.artists = artists
    }
    fun getArtists(): List<Artists> {
        return this.artists
    }
    fun setAvailable_markets(available_markets: List<String>) {
        this.available_markets = available_markets
    }
    fun getAvailable_markets(): List<String> {
        return this.available_markets
    }
    fun setDisc_number(disc_number: Int) {
        this.disc_number = disc_number
    }
    fun getDisc_number(): Int {
        return this.disc_number
    }
    fun setDuration_ms(duration_ms: Int) {
        this.duration_ms = duration_ms
    }
    fun getDuration_ms(): Int {
        return this.duration_ms
    }
    fun setExplicit(explicit: Boolean) {
        this.explicit = explicit
    }
    fun getExplicit(): Boolean {
        return this.explicit
    }
    fun setExternal_ids(external_ids: External_ids) {
        this.external_ids = external_ids
    }
    fun getExternal_ids(): External_ids {
        return this.external_ids
    }
    fun setExternal_urls(external_urls: External_urls) {
        this.external_urls = external_urls
    }
    fun getExternal_urls(): External_urls {
        return this.external_urls
    }
    fun setHref(href: String) {
        this.href = href
    }
    fun getHref(): String{
        return this.href
    }
    fun setId(id: String) {
        this.id = id
    }
    fun getId(): String {
        return this.id
    }
    fun setIs_local(is_local: Boolean) {
        this.is_local = is_local
    }
    fun getIs_local(): Boolean {
        return this.is_local
    }
    fun setName(name: String) {
        this.name = name
    }
    fun getName(): String {
        return this.name
    }
    fun setPopularity(popularity: Int) {
        this.popularity = popularity
    }
    fun getPopularity(): Int {
        return this.popularity
    }
    fun setPreview_url(preview_url: String) {
        this.preview_url = preview_url
    }
    fun getPreview_url(): String {
        return this.preview_url
    }
    fun setTrack_number(track_number: Int){
        this.track_number = track_number
    }
    fun getTrack_number(): Int{
        return this.track_number
    }
    fun setType(type: String){
        this.type = type
    }
    fun getType(): String{
        return this.type
    }
    fun setUri(uri: String){
        this.uri = uri
    }
    fun getUri(): String{
        return this.uri
    }
}

class Tracks {
    private var href: String = ""

    private lateinit var items: List<Items>

    private var limit: Int = 0

    private var next: String = ""

    private var offset: Int = 0

    private var previous: String = ""

    private var total = 0

    fun setHref(href: String) {
        this.href = href
    }
    fun getHref(): String {
        return this.href
    }
    fun setItems(items: List<Items>) {
        this.items = items
    }
    fun getItems(): List<Items>{
        return this.items
    }
    fun setLimit(limit: Int){
        this.limit = limit
    }
    fun getLimit(): Int{
        return this.limit
    }
    fun setNext(next: String){
        this.next = next
    }
    fun getNext(): String{
        return this.next
    }
    fun setOffset(offset: Int){
        this.offset = offset
    }
    fun getOffset(): Int{
        return this.offset
    }
    fun setPrevious(previous: String){
        this.previous = previous
    }
    fun getPrevious(): String{
        return this.previous
    }
    fun setTotal(total: Int){
        this.total = total
    }
    fun getTotal(): Int{
        return this.total
    }
}

class Root {
    private lateinit var tracks: Tracks

    fun setTracks(tracks: Tracks ) {
        this.tracks = tracks
    }
    fun getTracks(): Tracks {
        return this.tracks
    }
}
