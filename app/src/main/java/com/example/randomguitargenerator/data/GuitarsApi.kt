package com.example.randomguitargenerator.data

import retrofit2.http.GET

interface GuitarsApi {

    @GET("/randomguitar")
    suspend fun getRandomGuitar(): Guitar

    companion object {
        const val BASE_URL = "http://192.168.14.76:8080"
    }
}