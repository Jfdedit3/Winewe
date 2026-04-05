package com.jfdedit3.winewe.launch

import com.jfdedit3.winewe.model.ContainerProfile
import com.jfdedit3.winewe.model.ShortcutProfile

object LaunchScriptBuilder {
    fun build(container: ContainerProfile, shortcut: ShortcutProfile): String {
        val exports = buildMap {
            putAll(container.box64Preset.env)
            putAll(container.graphicsBackend.env)
            container.envVars.forEach { put(it.key, it.value) }
            if (shortcut.fullscreen) put("WINE_FULLSCREEN_FSR", "1")
        }.entries.joinToString("\n") { "export ${it.key}=\"${it.value}\"" }

        val args = shortcut.arguments.joinToString(" ") { "\"$it\"" }

        return """
#!/system/bin/sh
export WINEPREFIX=\"/data/data/com.jfdedit3.winewe/files/runtime/containers/${container.id}/prefix\"
export DISPLAY=\":0\"
$exports
wine \"${shortcut.executablePath}\" $args
""".trimIndent()
    }
}
