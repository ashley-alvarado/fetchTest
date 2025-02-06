package com.example.fetchtest.data

interface ItemsRepository {
    suspend fun getItems(): List<Item>
}

class ApiItemRepository (
    private val itemApiService: ItemApiService
): ItemsRepository {
    override suspend fun getItems(): List<Item> {
        return itemApiService.getItems()
    }
}