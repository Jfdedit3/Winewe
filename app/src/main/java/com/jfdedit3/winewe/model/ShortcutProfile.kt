package com.jfdedit3.winewe.model

data class ShortcutProfile(
    val id: String,
    val name: String,
    val containerId: String,
    val executablePath: String,
    val arguments: List<String> = emptyList(),
    val forceFullscreen: Boolean = false
)
