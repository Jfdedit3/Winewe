package com.jfdedit3.winewe.wine

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class WineManager(
    private val context: Context,
    private val config: WineConfig = WineConfig()
) {
    private val installDir = File(context.filesDir, config.installDirName)
    private val prefixDir = File(context.filesDir, config.prefixDirName)

    suspend fun isInstalled(): Boolean = withContext(Dispatchers.IO) {
        installDir.exists() && File(installDir, config.launcherScriptName).exists()
    }

    suspend fun installOrUpdate(): Result<String> = withContext(Dispatchers.IO) {
        runCatching {
            installDir.mkdirs()
            prefixDir.mkdirs()
            extractAssetFolder(config.assetRoot, installDir)
            val launcher = File(installDir, config.launcherScriptName)
            if (launcher.exists()) {
                launcher.setExecutable(true)
            }
            "Wine assets extracted to ${installDir.absolutePath}"
        }
    }

    suspend fun launch(executablePath: String? = null): Result<String> = withContext(Dispatchers.IO) {
        runCatching {
            val launcher = File(installDir, config.launcherScriptName)
            require(launcher.exists()) { "Launcher script not found" }

            val command = mutableListOf(
                launcher.absolutePath,
                prefixDir.absolutePath
            )

            if (!executablePath.isNullOrBlank()) {
                command += executablePath
            }

            val process = ProcessBuilder(command)
                .directory(installDir)
                .redirectErrorStream(true)
                .start()

            val output = process.inputStream.bufferedReader().use { it.readText() }
            val code = process.waitFor()
            "Exit code: $code\n$output"
        }
    }

    fun installPath(): String = installDir.absolutePath
    fun prefixPath(): String = prefixDir.absolutePath

    private fun extractAssetFolder(assetPath: String, destination: File) {
        val assets = context.assets
        val children = assets.list(assetPath).orEmpty()

        if (children.isEmpty()) {
            destination.parentFile?.mkdirs()
            assets.open(assetPath).use { input ->
                destination.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
            return
        }

        if (!destination.exists()) {
            destination.mkdirs()
        }

        for (child in children) {
            val childAssetPath = "$assetPath/$child"
            val childDest = File(destination, child)
            extractAssetFolder(childAssetPath, childDest)
        }
    }
}
