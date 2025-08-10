package com.example.randomator.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserList::class, ListItem::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun listDao(): ListDao
}
