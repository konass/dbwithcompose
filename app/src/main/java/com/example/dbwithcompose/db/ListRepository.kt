package com.example.dbwithcompose.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dbwithcompose.entities.ListItem
import kotlinx.coroutines.flow.Flow

interface ListRepository {

    fun getAllItems() : Flow<List<ListItem>>
    suspend fun insertItem (name: ListItem)
    suspend fun getItemById (id: Int) : ListItem?
    suspend fun delete(name: ListItem)
}