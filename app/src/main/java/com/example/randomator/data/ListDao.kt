package com.example.randomator.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ListDao {
    // Observe all lists with items
    @Transaction
    @Query("SELECT * FROM lists ORDER BY name")
    fun observeLists(): Flow<List<ListWithItems>>

    // Create
    @Insert
    suspend fun insertList(list: UserList): Long

    @Insert
    suspend fun insertItem(item: ListItem): Long

    // Delete
    @Query("DELETE FROM lists WHERE listId = :listId")
    suspend fun deleteList(listId: Long)

    @Query("DELETE FROM items WHERE itemId = :itemId")
    suspend fun deleteItem(itemId: Long)
}
