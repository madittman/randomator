package com.example.randomator.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.randomator.data.ListWithItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListsScreen(
    state: UiState,
    onCreateList: (String) -> Unit,
    onAddItem: (Long, String) -> Unit,
    onDeleteList: (Long) -> Unit,
    onDeleteItem: (Long) -> Unit
) {
    var newListName by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("My Lists") }) }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Row {
                OutlinedTextField(
                    value = newListName,
                    onValueChange = { newListName = it },
                    label = { Text("New list name") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(8.dp))
                Button(
                    onClick = {
                        onCreateList(newListName)
                        newListName = ""
                    }
                ) {
                    Text("Add")
                }
            }

            Spacer(Modifier.height(16.dp))

            if (state.isLoading) {
                LinearProgressIndicator(Modifier.fillMaxWidth())
            }

            state.error?.let { Text("Error: $it", color = MaterialTheme.colorScheme.error) }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.lists, key = { it.list.listId }) { lw ->
                    ListCard(
                        listWithItems = lw,
                        onAddItem = onAddItem,
                        onDeleteList = onDeleteList,
                        onDeleteItem = onDeleteItem
                    )
                }
            }
        }
    }
}

@Composable
private fun ListCard(
    listWithItems: ListWithItems,
    onAddItem: (Long, String) -> Unit,
    onDeleteList: (Long) -> Unit,
    onDeleteItem: (Long) -> Unit
) {
    var newItem by remember(listWithItems.list.listId) { mutableStateOf("") }

    ElevatedCard(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(text = listWithItems.list.name, style = MaterialTheme.typography.titleMedium)
                TextButton(onClick = { onDeleteList(listWithItems.list.listId) }) {
                    Text("Delete")
                }
            }
            Spacer(Modifier.height(8.dp))
            listWithItems.items.forEach { item ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(item.text)
                    TextButton(onClick = { onDeleteItem(item.itemId) }) { Text("Remove") }
                }
            }
            Spacer(Modifier.height(8.dp))
            Row {
                OutlinedTextField(
                    value = newItem,
                    onValueChange = { newItem = it },
                    label = { Text("New item") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(8.dp))
                Button(onClick = {
                    onAddItem(listWithItems.list.listId, newItem)
                    newItem = ""
                }) {
                    Text("Add")
                }
            }
        }
    }
}
