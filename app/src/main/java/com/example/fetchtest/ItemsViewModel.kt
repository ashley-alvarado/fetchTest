package com.example.fetchtest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.fetchtest.App.FetchApplication
import com.example.fetchtest.data.Item
import com.example.fetchtest.data.ItemsRepository
import kotlinx.coroutines.launch

class ItemsViewModel(private val itemsRepository: ItemsRepository): ViewModel() {
private val _uiState = MutableLiveData<List<Item>>(emptyList())
    val uiState: LiveData<List<Item>> get() = _uiState

    init {
        getItems()
    }

    fun getItems() {
        viewModelScope.launch {
            _uiState.value = try {
                itemsRepository
                    .getItems().filter { !it.name.isNullOrBlank() }
                    .sortedWith(compareBy(Item::listId, Item::name))
            } catch (e: Exception) {
                Log.e("error", e.toString())
                emptyList()
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FetchApplication)
                val itemsRepository = application.container.itemsRepository
                ItemsViewModel(itemsRepository = itemsRepository)
            }
        }
    }
}