package com.remziakgoz.wallsyfy.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

//https://pixabay.com/api/?key=47484736-416e0b62cb618ec6f78314d6f&order=lastest

private const val BASE_URL = "https://pixabay.com/"
private const val API_KEY = "47484736-416e0b62cb618ec6f78314d6f"

abstract class KtorApi {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json{
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    fun HttpRequestBuilder.pathUrl(path: String) {
        url {
            takeFrom(BASE_URL)
            path(path)
            parameter("key", API_KEY)
        }
    }
}