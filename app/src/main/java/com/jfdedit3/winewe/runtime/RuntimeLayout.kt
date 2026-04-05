package com.jfdedit3.winewe.runtime

import android.content.Context
import java.io.File

class RuntimeLayout(context: Context) {
    private val root = File(context.filesDir, "runtime")

    val containersDir = File(root, "containers")
    val rootfsDir = File(root, "rootfs")
    val shortcutsDir = File(root, "shortcuts")
    val tempDir = File(root, "tmp")
    val logsDir = File(root, "logs")
    val componentsDir = File(root, "components")

    fun ensureBaseLayout() {
        listOf(root, containersDir, rootfsDir, shortcutsDir, tempDir, logsDir, componentsDir).forEach {
            if (!it.exists()) it.mkdirs()
        }
    }

    fun containerDir(containerId: String): File = File(containersDir, containerId)
    fun prefixDir(containerId: String): File = File(containerDir(containerId), "prefix")
    fun drivesDir(containerId: String): File = File(containerDir(containerId), "drives")
    fun scriptsDir(containerId: String): File = File(containerDir(containerId), "scripts")
    fun configDir(containerId: String): File = File(containerDir(containerId), "config")
}
