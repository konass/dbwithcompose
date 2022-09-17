package com.example.dbwithcompose.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dbwithcompose.entities.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel


@Database (entities = [ListItem::class], version =1, exportSchema = true )
abstract class MainDataBase : RoomDatabase() {
abstract fun getDao() : Dao

}