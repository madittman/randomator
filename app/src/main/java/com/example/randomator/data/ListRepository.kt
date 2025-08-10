package com.example.randomator.data

import kotlinx.coroutines.flow.Flow

class ListRepository(private val dao: ListDao) {

    fun observeLists(): Flow<List<ListWithItems>> = dao.observeLists()

    suspend fun createList(name: String) {
        if (name.isBlank()) return
        dao.insertList(UserList(name = name.trim()))
    }

    suspend fun addItem(listId: Long, text: String) {
        if (text.isBlank()) return
        dao.insertItem(ListItem(listOwnerId = listId, text = text.trim()))
    }

    suspend fun deleteList(listId: Long) = dao.deleteList(listId)

    suspend fun deleteItem(itemId: Long) = dao.deleteItem(itemId)
}
