package com.mobileexam.deypalubos.network

import com.mobileexam.deypalubos.model.CharacterResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CharacterApiService {
    @GET("character")
    suspend fun getCharacters(): CharacterResponse

    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/"

        fun create(): CharacterApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CharacterApiService::class.java)
        }
    }
}