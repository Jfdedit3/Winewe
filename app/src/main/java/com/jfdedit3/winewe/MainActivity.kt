package com.jfdedit3.winewe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jfdedit3.winewe.wine.WineManager
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val wineManager = WineManager(this)

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    WineWeScreen(wineManager)
                }
            }
        }
    }
}

@Composable
private fun WineWeScreen(wineManager: WineManager) {
    val scope = rememberCoroutineScope()
    var status by remember { mutableStateOf("Ready") }
    var exePath by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "WineWe",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Android wrapper scaffold for a Wine runtime",
            style = MaterialTheme.typography.bodyLarge
        )

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Install path: ${wineManager.installPath()}")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Prefix path: ${wineManager.prefixPath()}")
            }
        }

        OutlinedTextField(
            value = exePath,
            onValueChange = { exePath = it },
            label = { Text("Windows executable path (optional)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(
            onClick = {
                scope.launch {
                    status = "Installing Wine assets..."
                    status = wineManager.installOrUpdate()
                        .fold(
                            onSuccess = { it },
                            onFailure = { it.stackTraceToString() }
                        )
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Install or update Wine runtime")
        }

        Button(
            onClick = {
                scope.launch {
                    status = "Launching..."
                    status = wineManager.launch(exePath.ifBlank { null })
                        .fold(
                            onSuccess = { it },
                            onFailure = { it.stackTraceToString() }
                        )
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Launch Wine")
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Runtime log",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(status)
            }
        }
    }
}
