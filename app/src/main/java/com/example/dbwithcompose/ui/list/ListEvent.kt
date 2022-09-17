package com.example.dbwithcompose.ui.list

import com.example.dbwithcompose.entities.ListItem

sealed class ListEvent {
    data class DeleteItem (val item: ListItem) : ListEvent()
    data class OnItemClick (val item: ListItem) : ListEvent()
    object OnUndoDeleteClick : ListEvent()
    object OnAddListClick: ListEvent()
}
