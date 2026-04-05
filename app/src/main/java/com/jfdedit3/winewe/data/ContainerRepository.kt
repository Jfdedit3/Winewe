package com.jfdedit3.winewe.data

import com.jfdedit3.winewe.model.Box64Preset
import com.jfdedit3.winewe.model.ContainerProfile
import com.jfdedit3.winewe.model.EnvironmentVariable
import com.jfdedit3.winewe.model.GraphicsDriver
import com.jfdedit3.winewe.model.ShortcutProfile

class ContainerRepository {
    fun sampleContainers(): List<ContainerProfile> {
        return listOf(
            ContainerProfile(
                id = "default-gaming",
                name = "Default Gaming",
                rootfsName = "ubuntu-focal",
                wineVersion = "wine-staging-9.x",
                screenWidth = 1280,
                screenHeight = 720,
                dpi = 160,
                box64Preset = Box64Preset.Performance,
                graphicsDriver = GraphicsDriver.Turnip,
                envVars = listOf(
                    EnvironmentVariable("DXVK_HUD", "0"),
                    EnvironmentVariable("WINEDEBUG", "-all")
                )
            ),
            ContainerProfile(
                id = "unity-safe",
                name = "Unity Safe",
                rootfsName = "ubuntu-focal",
                wineVersion = "wine-staging-9.x",
                screenWidth = 1600,
                screenHeight = 900,
                dpi = 180,
                box64Preset = Box64Preset.UnityGaming,
                graphicsDriver = GraphicsDriver.Zink,
                envVars = listOf(
                    EnvironmentVariable("MESA_EXTENSION_MAX_YEAR", "2003")
                )
            )
        )
    }

    fun sampleShortcuts(): List<ShortcutProfile> {
        return listOf(
            ShortcutProfile(
                id = "shortcut-skyrim",
                name = "Skyrim Launcher",
                containerId = "default-gaming",
                executablePath = "C:/Games/Skyrim/SkyrimLauncher.exe",
                forceFullscreen = true
            ),
            ShortcutProfile(
                id = "shortcut-unitygame",
                name = "Unity Game",
                containerId = "unity-safe",
                executablePath = "C:/Games/UnityGame/Game.exe",
                arguments = listOf("-force-gfx-direct")
            )
        )
    }
}
