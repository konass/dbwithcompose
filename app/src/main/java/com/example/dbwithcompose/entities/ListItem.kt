package com.example.dbwithcompose.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "list_item")
data class ListItem (
    @PrimaryKey val id: Int? = null,
@ColumnInfo (name = "title")
val title: String,
@ColumnInfo (name = "description")
val description: String,
)