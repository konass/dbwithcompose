package com.example.dbwithcompose.db

import androidx.room.*
import androidx.room.Dao
import com.example.dbwithcompose.entities.ListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query ("SELECT * FROM list_item" )
    fun getAllItems() : Flow<List<ListItem>>
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem (name: ListItem)
    @Query ("SELECT * FROM list_item WHERE id = :id")
    suspend fun getItemById (id: Int) : ListItem?
    @Delete
    suspend fun delete(name: ListItem)
}