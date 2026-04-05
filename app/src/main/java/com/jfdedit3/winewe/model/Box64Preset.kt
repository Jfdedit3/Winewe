package com.jfdedit3.winewe.model

enum class Box64Preset(
    val displayName: String,
    val env: Map<String, String>
) {
    Compatibility(
        displayName = "Compatibility",
        env = mapOf(
            "BOX64_DYNAREC" to "1",
            "BOX64_NOBANNER" to "1"
        )
    ),
    Performance(
        displayName = "Performance",
        env = mapOf(
            "BOX64_DYNAREC" to "1",
            "BOX64_FASTNAN" to "1",
            "BOX64_NOBANNER" to "1"
        )
    ),
    Stability(
        displayName = "Stability",
        env = mapOf(
            "BOX64_DYNAREC" to "1",
            "BOX64_SAFEFLAGS" to "1",
            "BOX64_NOBANNER" to "1"
        )
    ),
    UnityGaming(
        displayName = "Unity Gaming",
        env = mapOf(
            "BOX64_DYNAREC" to "1",
            "BOX64_NOBANNER" to "1",
            "MESA_EXTENSION_MAX_YEAR" to "2003"
        )
    )
}
