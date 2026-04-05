# WineWe Winlator-like Architecture

## Goal

Transform WineWe from a simple Wine wrapper into an Android application with a structure similar to Winlator-style launchers.

## Core modules

### 1. Container system
Each container stores:
- rootfs path
- wine prefix path
- selected Wine version
- selected Box64 preset
- graphics backend
- environment variables
- screen size and DPI
- startup executable / shortcut list

### 2. Runtime bootstrap
The bootstrap layer is responsible for:
- extracting bundled assets
- preparing rootfs folders
- preparing prefix folders
- generating launch scripts
- validating runtime components

### 3. Execution layer
The execution layer builds commands that can eventually integrate:
- PRoot
- Box64 / Box86
- Wine
- Mesa / Turnip / VirGL / Zink strategy
- DXVK / VKD3D / D8VK components

### 4. Shortcut layer
A shortcut is a saved application entry bound to a container with optional custom launch arguments.

### 5. UI layers
The UI should eventually include:
- Home dashboard
- Container list
- Create/edit container page
- Shortcut list
- Runtime diagnostics page
- Input profile manager

## What is already added in this repository

- Container models
- Graphics and preset models
- In-memory repository scaffold
- Runtime layout helper
- Bootstrap helper
- Launch script builder
- A new Compose screen showing containers and shortcuts

## What still needs to be added later

- Persistent storage (Room/DataStore)
- File picker for EXE/MSI/BAT
- PRoot integration
- Real Box64/Wine binaries
- Real graphics driver package handling
- Input controls overlay
- Per-game advanced settings
