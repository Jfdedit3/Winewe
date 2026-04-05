# WineWe

WineWe is an Android application scaffold designed to host and manage a Wine runtime inside an Android app project.

## What this repository contains

- A Kotlin + Jetpack Compose Android app
- A `WineManager` service layer for extracting and preparing Wine assets
- A minimal UI for runtime status, extraction, and launch actions
- GitHub Actions workflow to build debug APKs automatically

## Important note

This repository does **not** include compiled Wine binaries yet. The app is structured to:

1. package Wine runtime files in `app/src/main/assets/wine/`
2. extract them into the app private directory
3. mark launch scripts as executable
4. invoke the launcher from Android

To make Wine actually run, you still need to provide:

- compatible Wine binaries for the Android target ABI
- the required loader/runtime strategy
- any graphics / X11 / Wayland / container compatibility layer you want to use

## Project structure

- `app/src/main/java/com/jfdedit3/winewe/` Android app source
- `app/src/main/java/com/jfdedit3/winewe/wine/` Wine integration layer
- `app/src/main/assets/wine/` runtime placeholder assets and launch script
- `.github/workflows/android.yml` CI build workflow

## Build locally

```bash
./gradlew assembleDebug
```

## Next steps

- Add real Wine runtime files under `app/src/main/assets/wine/`
- Replace placeholder `wine-launch.sh`
- Add native helpers if your runtime needs JNI bridging
- Add a file picker and EXE association flow
