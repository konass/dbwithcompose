package com.example.dbwithcompose.ui.AddList

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dbwithcompose.Utils.UiEvent


@Composable
fun  AddListScreen (
    onPopBackStack :()-> Unit,
viewModel: AddListViewModel = hiltViewModel()
){
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true ) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                else -> Unit
            }
        }
    }
    Scaffold (
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = { 
                viewModel.onEvent(AddListEvent.OnSaveClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save"
                )
            }
        }

    ){
Column(
    modifier = Modifier.fillMaxSize()
){
    TextField(
        value = viewModel.title ,
        onValueChange = {
            viewModel.onEvent(AddListEvent.OnTitleChange(it))
        },
        placeholder = {
            Text(text= "Title" )
        },
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    TextField(
        value = viewModel.description ,
        onValueChange = {
            viewModel.onEvent(AddListEvent.OnDescriptionChange(it))
        },
        placeholder = {
            Text(text= "Description" )
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = false,
        maxLines = 5
    )

}
    }
}