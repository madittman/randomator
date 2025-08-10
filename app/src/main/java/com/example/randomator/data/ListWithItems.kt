package com.example.randomator.data

import androidx.room.Embedded
import androidx.room.Relation

data class ListWithItems(
    @Embedded val list: UserList,
    @Relation(
        parentColumn = "listId",
        entityColumn = "listOwnerId"
    )
    val items: List<ListItem>
)
