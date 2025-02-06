package com.example.fetchtest.data

import retrofit2.http.GET

interface ItemApiService {
    @GET("hiring.json")
    suspend fun getItems(): List<Item>
}