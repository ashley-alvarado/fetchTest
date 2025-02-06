package com.example.fetchtest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fetchtest.data.Item
import com.example.fetchtest.ui.theme.FetchTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FetchApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun FetchApp(modifier: Modifier = Modifier) {
    val viewModel: ItemsViewModel = viewModel(factory = ItemsViewModel.Factory)
    val items = viewModel.uiState.observeAsState()
    items.value?.let {
        ListOfItems(list = it, modifier = modifier)
    }
}

@Composable
fun ListOfItems(list: List<Item>, modifier: Modifier = Modifier) {
    LazyColumn (
        modifier = modifier
    ) {
        items(list) {
            Log.e("item", it.name + "  " + it.listId)
            Text(
                text = it.name,
                modifier = Modifier.padding(8.dp).padding(start = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FetchTestTheme {
        ListOfItems(
            listOf(Item(1, 2, "name"),
                Item(1, 2, "name"))
        )
    }
}