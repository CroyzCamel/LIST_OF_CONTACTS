package com.example.list_of_contacts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ContactApp(dbHelper: ContactDbHelper) {
    var contacts by remember { mutableStateOf(getAllContacts(dbHelper)) }
    var newName by remember { mutableStateOf("") }
    var newPhone by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.padding(16.dp)) { PaddingValues ->
        Column(modifier = Modifier.padding(PaddingValues)) {
            TextField(
                value = newName,
                onValueChange = { newName = it },
                label = { Text(text = "Name") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = newPhone,
                onValueChange = { newPhone = it },
                label = { Text(text = "Phone Number") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (newName.isNotBlank() && newPhone.isNotBlank()) {
                        coroutineScope.launch(Dispatchers.IO) {
                            addContact(dbHelper, newName, newPhone)
                            contacts = getAllContacts(dbHelper)
                            newName = ""
                            newPhone = ""
                        }

                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Add Contact")
            }
            Spacer(modifier = Modifier.height(16.dp))
            ContactList(contacts = contacts, onDeleteContact = { id ->
                coroutineScope.launch {
                    deleteContact(dbHelper, id)
                    contacts = getAllContacts(dbHelper)
                }
            })
        }
    }
}

@Composable
fun ContactList(contacts: List<Contact>, onDeleteContact: (Long) -> Unit) {
    LazyColumn {
        items(contacts) { contact ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = " Nome: ${contact.name}")
                    Text(text = " Número: ${contact.phone}")
                }
                IconButton(onClick = { onDeleteContact(contact.id) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
            HorizontalDivider()
        }
    }
}