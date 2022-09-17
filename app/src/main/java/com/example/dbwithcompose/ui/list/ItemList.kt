package com.example.dbwithcompose.ui.list

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dbwithcompose.entities.ListItem


@Composable

fun ItemList (
    item: ListItem,
    onEvent: (ListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
      modifier = modifier,
      verticalAlignment = Alignment.CenterVertically
) {
    Text(
        text = item.title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
     Spacer ( modifier = Modifier.width(150.dp))
     IconButton(onClick = {
         onEvent(ListEvent.DeleteItem(item))
     } ) {
         Icon (
             imageVector = Icons.Default.Delete,
             contentDescription = "Delete"
                 )
     }
}
}