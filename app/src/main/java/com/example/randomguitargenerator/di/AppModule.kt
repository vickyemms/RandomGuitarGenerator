package com.example.randomguitargenerator.di

import com.example.randomguitargenerator.data.GuitarsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGuitarsApi(): GuitarsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(GuitarsApi.BASE_URL)
            .build()
            .create(GuitarsApi::class.java)
    }
}