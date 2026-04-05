package com.jfdedit3.winewe.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jfdedit3.winewe.model.ContainerProfile
import com.jfdedit3.winewe.model.ShortcutProfile

@Composable
fun WinlatorLikeHomeScreen(
    containers: List<ContainerProfile>,
    shortcuts: List<ShortcutProfile>,
    status: String,
    onPrepareContainer: (ContainerProfile) -> Unit,
    onLaunchShortcut: (ShortcutProfile) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("WineWe", style = MaterialTheme.typography.headlineMedium)
            Text("Winlator-like container launcher scaffold", style = MaterialTheme.typography.bodyMedium)
        }

        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Status", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(status)
                }
            }
        }

        item { Text("Containers", style = MaterialTheme.typography.titleLarge) }

        items(containers) { container ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(container.name, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("Wine: ${container.wineVersion}")
                    Text("RootFS: ${container.rootfsName}")
                    Text("Graphics: ${container.graphicsBackend.displayName}")
                    Text("Preset: ${container.box64Preset.displayName}")
                    Text("Screen: ${container.screenWidth}x${container.screenHeight} @ ${container.dpi} dpi")
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(onClick = { onPrepareContainer(container) }) {
                        Text("Prepare container")
                    }
                }
            }
        }

        item { Text("Shortcuts", style = MaterialTheme.typography.titleLarge) }

        items(shortcuts) { shortcut ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(shortcut.name, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Container: ${shortcut.containerId}")
                        Text("EXE: ${shortcut.executablePath}")
                    }
                    Button(onClick = { onLaunchShortcut(shortcut) }) {
                        Text("Launch")
                    }
                }
            }
        }
    }
}
