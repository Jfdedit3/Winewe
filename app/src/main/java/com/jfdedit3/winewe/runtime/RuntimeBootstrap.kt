package com.jfdedit3.winewe.runtime

import android.content.Context
import com.jfdedit3.winewe.model.ContainerProfile
import java.io.File

class RuntimeBootstrap(private val context: Context) {
    private val runtimeRoot = File(context.filesDir, "runtime")

    fun prepareContainer(container: ContainerProfile): Result<String> = runCatching {
        val containerDir = File(runtimeRoot, "containers/${container.id}")
        val prefixDir = File(containerDir, "prefix")
        val driveDir = File(containerDir, "drive_c")
        val scriptsDir = File(containerDir, "scripts")

        listOf(runtimeRoot, containerDir, prefixDir, driveDir, scriptsDir).forEach {
            if (!it.exists()) it.mkdirs()
        }

        File(scriptsDir, "launch.sh").writeText(
            "# placeholder launcher for ${container.name}\n"
        )

        "Container prepared: ${container.name}\nPath: ${containerDir.absolutePath}"
    }
}
