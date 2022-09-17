package com.example.dbwithcompose.ui.AddList

 sealed class AddListEvent {
     data class OnTitleChange (val title: String ) : AddListEvent()
     data class OnDescriptionChange (val description: String ) : AddListEvent()
     object OnSaveClick: AddListEvent()
}