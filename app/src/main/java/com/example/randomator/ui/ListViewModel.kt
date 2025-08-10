package com.example.randomator.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomator.data.ListRepository
import com.example.randomator.data.ListWithItems
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class UiState(
    val lists: List<ListWithItems> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ListsViewModel(private val repo: ListRepository) : ViewModel() {

    private val _ui = MutableStateFlow(UiState(isLoading = true))
    val ui: StateFlow<UiState> = _ui.asStateFlow()

    init {
        repo.observeLists()
            .onStart { _ui.update { it.copy(isLoading = true) } }
            .catch { e -> _ui.update { it.copy(isLoading = false, error = e.message) } }
            .onEach { lists -> _ui.update { UiState(lists = lists, isLoading = false, error = null) } }
            .launchIn(viewModelScope)
    }

    fun createList(name: String) = viewModelScope.launch {
        repo.createList(name)
    }

    fun addItem(listId: Long, text: String) = viewModelScope.launch {
        repo.addItem(listId, text)
    }

    fun deleteList(listId: Long) = viewModelScope.launch {
        repo.deleteList(listId)
    }

    fun deleteItem(itemId: Long) = viewModelScope.launch {
        repo.deleteItem(itemId)
    }
}
