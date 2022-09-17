package com.example.dbwithcompose.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dbwithcompose.Utils.UiEvent


@Composable
fun ListScreen (
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: ListViewModel = hiltViewModel()
){
    val items = viewModel.items.collectAsState(initial = emptyList())
   val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{
            event->
            when(event) {
                is UiEvent.ShowSnackbar-> {
 val result = scaffoldState.snackbarHostState.showSnackbar(
     message = event.message,
     actionLabel = event.action
 )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(ListEvent.OnUndoDeleteClick)
                    }
                }
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
    Scaffold (
        scaffoldState = scaffoldState,
    floatingActionButton = {
        FloatingActionButton(onClick = {
        viewModel.onEvent(ListEvent.OnAddListClick)}) {
            Icon (
imageVector = Icons.Default.Add,
                contentDescription = "Add"
                    )
        }
    }
    ){
LazyColumn (
    modifier = Modifier.fillMaxSize()
        ){
    items(items.value) { item ->
        ItemList (
            item= item,
            onEvent = viewModel::onEvent,
            modifier = Modifier
                .fillMaxWidth()
                .clickable{
                    viewModel.onEvent(ListEvent.OnItemClick(item))
                }
                .padding(16.dp)
                )



    }
}
    }
}