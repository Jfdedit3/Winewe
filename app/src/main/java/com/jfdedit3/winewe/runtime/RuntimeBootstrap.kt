package com.jfdedit3.winewe.runtime

import android.content.Context
import com.jfdedit3.winewe.model.ContainerProfile
import java.io.File

class RuntimeBootstrap(
    private val context: Context,
    private val layout: RuntimeLayout = RuntimeLayout(context)
) {
    fun prepareContainer(container: ContainerProfile): Result<String> {
        return runCatching {
            layout.ensureBaseLayout()

            val containerDir = layout.containerDir(container.id)
            val prefixDir = layout.prefixDir(container.id)
            val drivesDir = layout.drivesDir(container.id)
            val scriptsDir = layout.scriptsDir(container.id)
            val configDir = layout.configDir(container.id)

            listOf(containerDir, prefixDir, drivesDir, scriptsDir, configDir).forEach {
                if (!it.exists()) it.mkdirs()
            }

            val desktop = File(drivesDir, "desktop")
            val downloads = File(drivesDir, "downloads")
            desktop.mkdirs()
            downloads.mkdirs()

            "Container prepared at ${containerDir.absolutePath}"
        }
    }
}
