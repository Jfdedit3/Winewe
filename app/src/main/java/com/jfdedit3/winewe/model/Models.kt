package com.jfdedit3.winewe.model

enum class Box64Preset(val displayName: String, val env: Map<String, String>) {
    Compatibility("Compatibility", mapOf("BOX64_DYNAREC" to "1", "BOX64_NOBANNER" to "1")),
    Performance("Performance", mapOf("BOX64_DYNAREC" to "1", "BOX64_FASTNAN" to "1", "BOX64_NOBANNER" to "1")),
    Stability("Stability", mapOf("BOX64_DYNAREC" to "1", "BOX64_SAFEFLAGS" to "1", "BOX64_NOBANNER" to "1"))
}

enum class GraphicsBackend(val displayName: String, val env: Map<String, String>) {
    Zink("Zink", mapOf("GALLIUM_DRIVER" to "zink")),
    Virpipe("Virpipe", mapOf("GALLIUM_DRIVER" to "virpipe")),
    Native("Native", emptyMap())
}

data class EnvVar(val key: String, val value: String)

data class ContainerProfile(
    val id: String,
    val name: String,
    val rootfsName: String,
    val wineVersion: String,
    val screenWidth: Int,
    val screenHeight: Int,
    val dpi: Int,
    val box64Preset: Box64Preset,
    val graphicsBackend: GraphicsBackend,
    val envVars: List<EnvVar> = emptyList()
)

data class ShortcutProfile(
    val id: String,
    val name: String,
    val containerId: String,
    val executablePath: String,
    val arguments: List<String> = emptyList(),
    val fullscreen: Boolean = false
)
