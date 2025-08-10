package com.example.randomator.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lists")
data class UserList(
    @PrimaryKey(autoGenerate = true) val listId: Long = 0,
    val name: String
)
