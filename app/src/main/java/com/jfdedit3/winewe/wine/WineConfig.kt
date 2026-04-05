package com.jfdedit3.winewe.wine

data class WineConfig(
    val assetRoot: String = "wine",
    val installDirName: String = "wine-runtime",
    val prefixDirName: String = "wine-prefix",
    val launcherScriptName: String = "wine-launch.sh"
)
