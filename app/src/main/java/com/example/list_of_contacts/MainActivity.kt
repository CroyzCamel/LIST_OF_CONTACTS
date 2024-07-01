package com.example.list_of_contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.list_of_contacts.ui.theme.List_of_contactsTheme

class MainActivity : ComponentActivity() {
    private lateinit var dbHelper: ContactDbHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = ContactDbHelper(this@MainActivity)
        enableEdgeToEdge()
        setContent {
            List_of_contactsTheme {
               ContactApp(dbHelper = dbHelper)
            }
        }
    }
}

