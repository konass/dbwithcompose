package com.example.dbwithcompose.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbwithcompose.Utils.Routes
import com.example.dbwithcompose.Utils.UiEvent
import com.example.dbwithcompose.db.ListRepository
import com.example.dbwithcompose.db.ListRepositoryImpl
import com.example.dbwithcompose.entities.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository : ListRepository):  ViewModel() {
    val items = repository.getAllItems()
    val _uiEvent = Channel<UiEvent> ()
    val uiEvent = _uiEvent.receiveAsFlow()
    private var deletedItem : ListItem? = null
    fun onEvent (event: ListEvent) {
        when (event) {
            is ListEvent.DeleteItem -> {
                viewModelScope.launch {
                    deletedItem = event.item
                    repository.delete(event.item)
                    sendUiEvent(UiEvent.ShowSnackbar(
                        message= "Item deleted",
                        action = "Undo"
                    ))
                }
            }
            is ListEvent.OnAddListClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_ITEM_LIST))
            }
            is ListEvent.OnItemClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_ITEM_LIST + "?listId=${event.item.id}"))
            }
            is ListEvent.OnUndoDeleteClick -> {
                deletedItem?.let { item ->
                    viewModelScope.launch {
                        repository.insertItem(item)
                    }
                }
            }
        }
    }
private fun sendUiEvent (event: UiEvent) {
    viewModelScope.launch {
        _uiEvent.send(event)
    }
}
}