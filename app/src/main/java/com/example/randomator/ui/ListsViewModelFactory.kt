package com.example.randomator.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.randomator.data.ListRepository

class ListsViewModelFactory(private val repo: ListRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListsViewModel(repo) as T
    }
}
