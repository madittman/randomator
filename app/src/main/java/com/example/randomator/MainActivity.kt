package com.example.randomator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.room.Room
import com.example.randomator.data.AppDatabase
import com.example.randomator.data.ListRepository
import com.example.randomator.ui.ListsScreen
import com.example.randomator.ui.ListsViewModel
import com.example.randomator.ui.ListsViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Build DB & repo (for a real app, prefer DI like Hilt)
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "lists.db").build()
        val repo = ListRepository(db.listDao())
        val factory = ListsViewModelFactory(repo)

        setContent {
            val vm: ListsViewModel by viewModels { factory }
            val state = vm.ui.collectAsState()

            ListsScreen(
                state = state.value,
                onCreateList = vm::createList,
                onAddItem = vm::addItem,
                onDeleteList = vm::deleteList,
                onDeleteItem = vm::deleteItem
            )
        }
    }
}
