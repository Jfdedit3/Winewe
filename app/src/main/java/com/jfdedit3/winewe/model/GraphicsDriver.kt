package com.jfdedit3.winewe.model

enum class GraphicsDriver(
    val displayName: String,
    val env: Map<String, String>
) {
    Turnip(
        displayName = "Turnip",
        env = mapOf(
            "GALLIUM_DRIVER" to "zink",
            "TU_DEBUG" to "nogmem"
        )
    ),
    VirGL(
        displayName = "VirGL",
        env = mapOf(
            "GALLIUM_DRIVER" to "virpipe"
        )
    ),
    Zink(
        displayName = "Zink",
        env = mapOf(
            "GALLIUM_DRIVER" to "zink"
        )
    ),
    Vortek(
        displayName = "Vortek",
        env = emptyMap()
    )
}
