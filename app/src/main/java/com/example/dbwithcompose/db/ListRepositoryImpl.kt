package com.example.dbwithcompose.db

import com.example.dbwithcompose.entities.ListItem
import kotlinx.coroutines.flow.Flow

class ListRepositoryImpl (private val dao : Dao) : ListRepository {
    override fun getAllItems(): Flow<List<ListItem>> {
        return dao.getAllItems()
    }

    override suspend fun insertItem(name: ListItem) {
       dao.insertItem(name)
    }

    override suspend fun getItemById(id: Int): ListItem? {
        return dao.getItemById(id)
    }

    override suspend fun delete(name: ListItem) {
       dao.delete(name)
    }

}