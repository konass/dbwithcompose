package com.example.dbwithcompose.ui.AddList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dbwithcompose.Utils.UiEvent
import com.example.dbwithcompose.db.ListRepository
import com.example.dbwithcompose.entities.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddListViewModel @Inject constructor(private val repository: ListRepository, savedStateHandle: SavedStateHandle) : ViewModel(){
var item by mutableStateOf<ListItem?>(null)
    private set
    var title by mutableStateOf("")
    private set
    var description by mutableStateOf("")
    private set
    val _uiEvent = Channel<UiEvent> ()
    val uiEvent = _uiEvent.receiveAsFlow()
    init{
        val itemId = savedStateHandle.get<Int> ("itemId")!!
if (itemId!=-1){
    viewModelScope.launch {
       repository.getItemById(itemId)?.let { item->
           title = item.title
           description = item.description
           this@AddListViewModel.item = item
       }

    }
}
    }
    fun onEvent (event : AddListEvent ) {
        when (event) {
            is AddListEvent.OnTitleChange ->{
                title = event.title
            }
            is AddListEvent.OnDescriptionChange ->{
               description= event.description
            }
            is AddListEvent.OnSaveClick -> {
                viewModelScope.launch {
                    if (title.isBlank()) {
                        sendUiEvent(UiEvent.ShowSnackbar(
                            message = "The title can't be empty"
                        ))
                        return@launch
                    }
                    repository.insertItem(
                        ListItem(
                            id= item?.id,
                            title = title,
                            description = description
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
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