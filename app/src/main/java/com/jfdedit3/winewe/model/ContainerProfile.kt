package com.jfdedit3.winewe.model

data class ContainerProfile(
    val id: String,
    val name: String,
    val rootfsName: String,
    val wineVersion: String,
    val screenWidth: Int,
    val screenHeight: Int,
    val dpi: Int,
    val box64Preset: Box64Preset,
    val graphicsDriver: GraphicsDriver,
    val envVars: List<EnvironmentVariable> = emptyList(),
    val wow64Mode: Boolean = true
)
