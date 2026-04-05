package com.jfdedit3.winewe.data

import com.jfdedit3.winewe.model.Box64Preset
import com.jfdedit3.winewe.model.ContainerProfile
import com.jfdedit3.winewe.model.EnvVar
import com.jfdedit3.winewe.model.GraphicsBackend
import com.jfdedit3.winewe.model.ShortcutProfile

class ContainerRepository {
    fun sampleContainers(): List<ContainerProfile> = listOf(
        ContainerProfile(
            id = "default-gaming",
            name = "Default Gaming",
            rootfsName = "ubuntu-focal",
            wineVersion = "wine-staging-9.x",
            screenWidth = 1280,
            screenHeight = 720,
            dpi = 160,
            box64Preset = Box64Preset.Performance,
            graphicsBackend = GraphicsBackend.Zink,
            envVars = listOf(EnvVar("WINEDEBUG", "-all"))
        ),
        ContainerProfile(
            id = "safe-mode",
            name = "Safe Mode",
            rootfsName = "ubuntu-focal",
            wineVersion = "wine-staging-9.x",
            screenWidth = 1024,
            screenHeight = 768,
            dpi = 160,
            box64Preset = Box64Preset.Stability,
            graphicsBackend = GraphicsBackend.Virpipe
        )
    )

    fun sampleShortcuts(): List<ShortcutProfile> = listOf(
        ShortcutProfile(
            id = "shortcut-notepad",
            name = "Notepad",
            containerId = "default-gaming",
            executablePath = "C:/windows/notepad.exe"
        ),
        ShortcutProfile(
            id = "shortcut-game",
            name = "Game Launcher",
            containerId = "safe-mode",
            executablePath = "C:/Games/Game/Game.exe",
            arguments = listOf("-windowed"),
            fullscreen = true
        )
    )
}
