// To parse the JSON, install jackson-module-kotlin and do:
//
//   val song = Song.fromJson(jsonString)

package com.example.apidespotify

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.core.*
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.node.*
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.fasterxml.jackson.module.kotlin.*
import kotlin.reflect.KClass


@Suppress("UNCHECKED_CAST")
private fun <T> ObjectMapper.convert(k: KClass<*>, fromJson: (JsonNode) -> T, toJson: (T) -> String, isUnion: Boolean = false) = registerModule(SimpleModule().apply {
    addSerializer(k.java as Class<T>, object : StdSerializer<T>(k.java as Class<T>) {
        override fun serialize(value: T, gen: JsonGenerator, provider: SerializerProvider) = gen.writeRawValue(toJson(value))
    })
    addDeserializer(k.java as Class<T>, object : StdDeserializer<T>(k.java as Class<T>) {
        override fun deserialize(p: JsonParser, ctxt: DeserializationContext) = fromJson(p.readValueAsTree())
    })
})

val mapper = jacksonObjectMapper().apply {
    propertyNamingStrategy = PropertyNamingStrategy.LOWER_CAMEL_CASE
    setSerializationInclusion(JsonInclude.Include.NON_NULL)
    convert(AlbumTypeEnum::class,        { AlbumTypeEnum.fromValue(it.asText()) },        { "\"${it.value}\"" })
    convert(ArtistType::class,           { ArtistType.fromValue(it.asText()) },           { "\"${it.value}\"" })
    convert(ReleaseDatePrecision::class, { ReleaseDatePrecision.fromValue(it.asText()) }, { "\"${it.value}\"" })
    convert(ItemType::class,             { ItemType.fromValue(it.asText()) },             { "\"${it.value}\"" })
}

data class Song (
    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val tracks: Tracks
) {
    fun toJson() = mapper.writeValueAsString(this)

    companion object {
        fun fromJson(json: String) = mapper.readValue<Song>(json)
    }
}

data class Tracks (
    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val href: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val items: List<Item>,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val limit: Long,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val next: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val offset: Long,

    val previous: Any? = null,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val total: Long
)

data class Item (
    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val album: Album,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val artists: List<Artist>,

    @get:JsonProperty("available_markets", required=true)@field:JsonProperty("available_markets", required=true)
    val availableMarkets: List<String>,

    @get:JsonProperty("disc_number", required=true)@field:JsonProperty("disc_number", required=true)
    val discNumber: Long,

    @get:JsonProperty("duration_ms", required=true)@field:JsonProperty("duration_ms", required=true)
    val durationMS: Long,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val explicit: Boolean,

    @get:JsonProperty("external_ids", required=true)@field:JsonProperty("external_ids", required=true)
    val externalIDS: ExternalIDS,

    @get:JsonProperty("external_urls", required=true)@field:JsonProperty("external_urls", required=true)
    val externalUrls: ExternalUrls,

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

    @get:JsonProperty("preview_url")@field:JsonProperty("preview_url")
    val previewURL: String? = null,

    @get:JsonProperty("track_number", required=true)@field:JsonProperty("track_number", required=true)
    val trackNumber: Long,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val type: ItemType,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val uri: String
)

data class Album (
    @get:JsonProperty("album_type", required=true)@field:JsonProperty("album_type", required=true)
    val albumType: AlbumTypeEnum,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val artists: List<Artist>,

    @get:JsonProperty("available_markets", required=true)@field:JsonProperty("available_markets", required=true)
    val availableMarkets: List<String>,

    @get:JsonProperty("external_urls", required=true)@field:JsonProperty("external_urls", required=true)
    val externalUrls: ExternalUrls,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val href: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val id: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val images: List<Image>,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val name: String,

    @get:JsonProperty("release_date", required=true)@field:JsonProperty("release_date", required=true)
    val releaseDate: String,

    @get:JsonProperty("release_date_precision", required=true)@field:JsonProperty("release_date_precision", required=true)
    val releaseDatePrecision: ReleaseDatePrecision,

    @get:JsonProperty("total_tracks", required=true)@field:JsonProperty("total_tracks", required=true)
    val totalTracks: Long,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val type: AlbumTypeEnum,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val uri: String
)

enum class AlbumTypeEnum(val value: String) {
    Album("album"),
    Compilation("compilation"),
    Single("single");

    companion object {
        fun fromValue(value: String): AlbumTypeEnum = when (value) {
            "album"       -> Album
            "compilation" -> Compilation
            "single"      -> Single
            else          -> throw IllegalArgumentException()
        }
    }
}

data class Artist (
    @get:JsonProperty("external_urls", required=true)@field:JsonProperty("external_urls", required=true)
    val externalUrls: ExternalUrls,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val href: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val id: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val name: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val type: ArtistType,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val uri: String
)

data class ExternalUrls (
    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val spotify: String
)

enum class ArtistType(val value: String) {
    Artist("artist");

    companion object {
        fun fromValue(value: String): ArtistType = when (value) {
            "artist" -> Artist
            else     -> throw IllegalArgumentException()
        }
    }
}

data class Image (
    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val height: Long,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val url: String,

    @get:JsonProperty(required=true)@field:JsonProperty(required=true)
    val width: Long
)

enum class ReleaseDatePrecision(val value: String) {
    Day("day"),
    Year("year");

    companion object {
        fun fromValue(value: String): ReleaseDatePrecision = when (value) {
            "day"  -> Day
            "year" -> Year
            else   -> throw IllegalArgumentException()
        }
    }
}

data class ExternalIDS (
    @get:JsonProperty("isrc", required=true)@field:JsonProperty("isrc", required=true)
    val isrc: String
)

enum class ItemType(val value: String) {
    Track("track");

    companion object {
        fun fromValue(value: String): ItemType = when (value) {
            "track" -> Track
            else    -> throw IllegalArgumentException()
        }
    }
}
