# Android Task Manager App

A clean, modern, and offline-first task management application built for Android. This project showcases standard modern Android development practices, demonstrating clean architecture, declarative UI design, reactive programming, and dependency injection.

---

## 🌟 Key Features

* **Task CRUD Operations**: Seamlessly create, read, update, and delete tasks.
* **Status Toggling**: Instantly mark tasks as completed or pending directly from the home feed.
* **Priority Classification**: Categorize tasks into **Low**, **Medium**, or **High** priority levels with visual indicators.
* **Search Functionality**: Query-based, case-insensitive instant search filtering across task titles and descriptions.
* **Beautiful Material 3 UI**: Polished look using Material 3 UI components, featuring support for dark/light themes and modern edge-to-edge layout styling.
* **Offline-First Persistence**: Powered by a local SQLite database using Room, ensuring state is preserved across app relaunches.

---

## 🛠️ Tech Stack & Libraries

* **Core Language**: [Kotlin](https://kotlinlang.org/)
* **UI Framework**: [Jetpack Compose](https://developer.android.com/compose) (with Material 3 components)
* **Architecture Pattern**: MVVM (Model-View-ViewModel) + Clean Architecture
* **Dependency Injection**: [Hilt (Dagger)](https://developer.android.com/training/dependency-injection/hilt-android)
* **Local Persistence**: [Room Database](https://developer.android.com/training/data-storage/room)
* **Asynchronous Flow**: Kotlin Coroutines & reactive [Flow / StateFlow](https://kotlinlang.org/docs/flow.html)
* **Navigation**: [Compose Navigation Component](https://developer.android.com/develop/ui/compose/navigation)

---

## 📐 Architecture & Clean Design

The app follows the official Google architecture guidelines, dividing the codebase into logical layers:

1. **Domain Layer (`data/model`)**: Contains clean domain models (e.g., `Task`, `TaskPriority`) decoupled from persistence logic.
2. **Data Layer (`data/local`, `data/repository`)**: Manages the SQLite database via Room, mapping database entities (`TaskEntity`) to domain entities (`Task`). The repository serves as the single source of truth.
3. **UI / Presentation Layer (`ui/screens`, `ui/components`)**: Uses ViewModels to expose state reactively through `StateFlow` to Compose screens, keeping UI components stateless and lightweight.

---

## 📸 Screenshots

| Splash Screen | Home Screen / List | Add & Edit Task | About / Settings |
|:---:|:---:|:---:|:---:|
| *Placeholder* | *Placeholder* | *Placeholder* | *Placeholder* |

---

## 🚀 Setup & Build Instructions

### Prerequisites
* **Android Studio** (Koala or newer recommended)
* **JDK 17** or higher
* **Android SDK** API level 34+

### Installation & Run
1. Clone this repository:
   ```bash
   git clone https://github.com/TSR0705/Android-TaskManager-App.git
   cd Android-TaskManager-App
   ```
2. Open the project in Android Studio.
3. Allow Gradle to sync dependencies.
4. Select a virtual device (Emulator) or connect a physical device.
5. Click the **Run** button (`Shift + F10` / `Control + R`).

### Build APK
To build a debug APK from the command line:
* **Windows (PowerShell/CMD)**:
  ```powershell
  ./gradlew assembleDebug
  ```
* **macOS / Linux**:
  ```bash
  ./gradlew assembleDebug
  ```
The generated APK will be available in:
`app/build/outputs/apk/debug/app-debug.apk`

---

## 🔮 Future Roadmap

* [ ] **Local Notifications**: Scheduled notifications for task due dates.
* [ ] **Category Tags**: Support for grouping tasks by labels (e.g., Work, Personal, Shopping).
* [ ] **Data Export/Import**: Ability to backup and restore tasks to/from local storage.
* [ ] **Biometric Lock**: Privacy lock to secure task logs.

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
