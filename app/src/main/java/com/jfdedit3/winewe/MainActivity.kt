package com.jfdedit3.winewe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.jfdedit3.winewe.data.ContainerRepository
import com.jfdedit3.winewe.launch.LaunchScriptBuilder
import com.jfdedit3.winewe.runtime.RuntimeBootstrap
import com.jfdedit3.winewe.ui.WinlatorLikeHomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = ContainerRepository()
        val bootstrap = RuntimeBootstrap(this)

        setContent {
            MaterialTheme {
                Surface {
                    val containers = remember { repository.sampleContainers() }
                    val shortcuts = remember { repository.sampleShortcuts() }
                    var status by remember { mutableStateOf("Ready") }

                    WinlatorLikeHomeScreen(
                        containers = containers,
                        shortcuts = shortcuts,
                        status = status,
                        onPrepareContainer = { container ->
                            status = bootstrap.prepareContainer(container).fold(
                                onSuccess = { it },
                                onFailure = { it.stackTraceToString() }
                            )
                        },
                        onLaunchShortcut = { shortcut ->
                            val container = containers.firstOrNull { it.id == shortcut.containerId }
                            status = if (container == null) {
                                "Container not found for shortcut ${shortcut.name}"
                            } else {
                                LaunchScriptBuilder.build(container, shortcut)
                            }
                        }
                    )
                }
            }
        }
    }
}
