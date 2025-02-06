package com.example.fetchtest.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface FetchContainer {
    val itemsRepository: ItemsRepository
}

class AppContainer : FetchContainer {
    private val fetchUrl = "https://fetch-hiring.s3.amazonaws.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(fetchUrl)
        .build()

    private val retrofitService: ItemApiService by lazy {
        retrofit.create(ItemApiService::class.java)
    }

    override val itemsRepository: ItemsRepository by lazy {
        ApiItemRepository(retrofitService)
    }
}