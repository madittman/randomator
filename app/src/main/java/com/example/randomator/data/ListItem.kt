package com.example.randomator.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "items",
    foreignKeys = [
        ForeignKey(
            entity = UserList::class,
            parentColumns = ["listId"],
            childColumns = ["listOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("listOwnerId")]
)
data class ListItem(
    @PrimaryKey(autoGenerate = true) val itemId: Long = 0,
    val listOwnerId: Long,
    val text: String
)
