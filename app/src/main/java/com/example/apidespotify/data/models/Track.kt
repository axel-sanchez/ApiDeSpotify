// To parse the JSON, install jackson-module-kotlin and do:
//
//   val track = Track.fromJson(jsonString)

package com.example.apidespotify.data.models

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.module.kotlin.*

val mapperTrack = jacksonObjectMapper().apply {
    propertyNamingStrategy = PropertyNamingStrategy.LOWER_CAMEL_CASE
    setSerializationInclusion(JsonInclude.Include.NON_NULL)
}

data class Track (
    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val album: AlbumTrack,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val artists: List<ArtistTrack>,

    @get:JsonProperty("available_markets", required=true)@field:JsonProperty("available_markets", required=true)
    val availableMarkets: List<String>,

    @get:JsonProperty("disc_number", required=true)@field:JsonProperty("disc_number", required=true)
    val discNumber: Long,

    @get:JsonProperty("duration_ms", required=true)@field:JsonProperty("duration_ms", required=true)
    val durationMS: Long,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val explicit: Boolean,

    @get:JsonProperty("external_ids", required=true)@field:JsonProperty("external_ids", required=true)
    val externalIDS: ExternalIDSTrack,

    @get:JsonProperty("external_urls", required=true)@field:JsonProperty("external_urls", required=true)
    val externalUrls: ExternalUrlsTrack,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val href: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val id: String,

    @get:JsonProperty("is_local", required=true)@field:JsonProperty("is_local", required=true)
    val isLocal: Boolean,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val name: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val popularity: Long,

    @get:JsonProperty("preview_url", required=true)@field:JsonProperty("preview_url", required=true)
    val previewURL: String,

    @get:JsonProperty("track_number", required=true)@field:JsonProperty("track_number", required=true)
    val trackNumber: Long,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val type: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val uri: String
) {
    fun toJson() = mapperTrack.writeValueAsString(this)

    companion object {
        fun fromJson(json: String) = mapperTrack.readValue<Track>(json)
    }
}

data class AlbumTrack (
    @get:JsonProperty("album_type", required=true)@field:JsonProperty("album_type", required=true)
    val albumType: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val artists: List<ArtistTrack>,

    @get:JsonProperty("available_markets", required=true)@field:JsonProperty("available_markets", required=true)
    val availableMarkets: List<String>,

    @get:JsonProperty("external_urls", required=true)@field:JsonProperty("external_urls", required=true)
    val externalUrls: ExternalUrlsTrack,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val href: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val id: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val images: List<ImageTrack>,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val name: String,

    @get:JsonProperty("release_date", required=true)@field:JsonProperty("release_date", required=true)
    val releaseDate: String,

    @get:JsonProperty("release_date_precision", required=true)@field:JsonProperty("release_date_precision", required=true)
    val releaseDatePrecision: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val type: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val uri: String
)

data class ArtistTrack (
    @get:JsonProperty("external_urls", required=true)@field:JsonProperty("external_urls", required=true)
    val externalUrls: ExternalUrlsTrack,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val href: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val id: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val name: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val type: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val uri: String
)

data class ExternalUrlsTrack (
    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val spotify: String
)

data class ImageTrack (
    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val height: Long,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val url: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val width: Long
)

data class ExternalIDSTrack (
    @get:JsonProperty("isrc", required=true)@field:JsonProperty("isrc", required=true)
    val isrc: String
)