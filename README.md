# 🎯 Task Manager

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-Purple.svg?style=flat&logo=kotlin)](https://kotlinlang.org/)
[![Compose](https://img.shields.io/badge/Jetpack_Compose-Material_3-blue.svg?style=flat&logo=android)](https://developer.android.com/compose)
[![Room](https://img.shields.io/badge/Room-Database-green.svg?style=flat&logo=sqlite)](https://developer.android.com/training/data-storage/room)
[![Hilt](https://img.shields.io/badge/Hilt-DI-orange.svg?style=flat)](https://developer.android.com/training/dependency-injection/hilt-android)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

An elegant, offline-first task management Android application demonstrating modern architecture patterns (MVVM + clean architecture), declarative UI, reactive data flow, and dependency injection.

---

## 📸 App Screenshots

Below is a gallery of the Task Manager app in action, showing the splash screen, task list, styling, and forms:

<div align="center">
  <table>
    <tr>
      <td><img src="pictures/Screenshot%202026-05-26%20094418.png" width="180" alt="Splash Screen"/><br/><sub><b>Splash Screen</b></sub></td>
      <td><img src="pictures/Screenshot_20260526_104625_Task%20Manager.jpg.jpeg" width="180" alt="Home Screen"/><br/><sub><b>Home Screen</b></sub></td>
      <td><img src="pictures/6100406109853126401.jpg" width="180" alt="Add Task Screen"/><br/><sub><b>Add Task</b></sub></td>
      <td><img src="pictures/6100406109853126399.jpg" width="180" alt="Task Priority Detail"/><br/><sub><b>Task Customization</b></sub></td>
      <td><img src="pictures/6100406109853126400.jpg" width="180" alt="About Screen"/><br/><sub><b>About Screen</b></sub></td>
    </tr>
  </table>
</div>

---

## ⚡ Tech Stack & Architecture

This project is built using industry-standard libraries and architectures:

* **Core & Async**: [Kotlin](https://kotlinlang.org/), Coroutines, and [Flow / StateFlow](https://kotlinlang.org/docs/flow.html) for reactive streams.
* **UI**: [Jetpack Compose](https://developer.android.com/compose) utilizing Material 3 components and edge-to-edge layouts.
* **Database**: [Room Database](https://developer.android.com/training/data-storage/room) for persistent local cache.
* **Dependency Injection**: [Hilt (Dagger)](https://developer.android.com/training/dependency-injection/hilt-android) for class decoupling and testability.
* **Navigation**: Type-safe navigation utilizing the [Compose Navigation Component](https://developer.android.com/develop/ui/compose/navigation).

### Architecture Flow

The codebase follows the official Android Architecture Guidelines (MVVM with Clean Architecture flow):

```mermaid
graph TD
    subgraph UI [UI Layer]
        A[MainActivity] --> B[TaskNavGraph]
        B --> C[Screens: Splash / Home / Add-Edit / About]
        C <--> D[ViewModels: HomeViewModel / AddEditViewModel]
    end
    subgraph Domain [Domain Layer]
        D <--> E[Task Domain Model]
    end
    subgraph Data [Data Layer]
        D --> F[TaskRepository]
        F --> G[TaskDao]
        G --> H[(Room SQLite Database)]
        G --> I[TaskEntity]
        I <-->|Mappers| E
    end
    
    style H fill:#4CAF50,stroke:#388E3C,stroke-width:2px,color:#fff
    style E fill:#2196F3,stroke:#1976D2,stroke-width:2px,color:#fff
    style D fill:#FF9800,stroke:#F57C00,stroke-width:2px,color:#fff
```

---

## 🚀 Getting Started

### Prerequisites
* **Android Studio** (Koala or newer)
* **JDK 17**
* **Android SDK** API level 34+

### Clone & Build
```bash
git clone https://github.com/TSR0705/Android-TaskManager-App.git
cd Android-TaskManager-App
```
1. Open the folder in Android Studio and let Gradle sync.
2. Select an emulator or connect a device and click **Run** (`Shift + F10`).

### Build APK
Generate a debug APK from the root directory:
```powershell
# Windows
./gradlew assembleDebug

# macOS / Linux
./gradlew assembleDebug
```
The compiled output is located at: `app/build/outputs/apk/debug/app-debug.apk`

---

## 📄 License
Licensed under the [MIT License](LICENSE).
