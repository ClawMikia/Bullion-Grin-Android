# Bullion Grin 🪙

A modern personal savings tracker Android app with gamification elements designed to help you build lasting wealth through consistent habits.

## 🚀 Features

- **✅ Smart Tracking** — Log savings with amount, frequency (Daily, Weekly, Monthly, Quarterly, Annually), and optional notes.
- **📊 Detailed Records** — Browse all your contributions with built-in frequency filtering.
- **🌍 Global Support** — Multi-currency support using system locales and persistent preferences.
- **🌙 Theme Options** — Dark mode support for comfortable viewing in any environment.
- **🔔 Daily Reminders** — Automated notifications via WorkManager to keep you on track.
- **🎮 Gamification Engine**
  - **Streaks:** Maintain your saving momentum (resets after 48h of inactivity).
  - **XP System:** Earn 10 XP per save plus bonuses based on the amount saved.
  - **Achievements:** Unlock milestones like `First Save`, `7-Day Streak`, and `1000 Savings Milestone`.

## 🛠 Tech Stack

| Layer | Technology |
|---|---|
| **Language** | Kotlin 1.9.22 |
| **UI Framework** | Material Components & View Binding |
| **Architecture** | MVVM (ViewModel + LiveData) |
| **Database** | Room Persistence Library |
| **Concurrency** | Kotlin Coroutines |
| **Background** | WorkManager |
| **Build System** | Gradle 8.6 with Kotlin/Groovy DSL |

## 📁 Project Structure

```text
app/src/main/java/com/bulliongrin/app/
├── data/
│   ├── dao/           # Room Data Access Objects
│   ├── database/      # Database configuration & migrations
│   ├── entity/        # Data models for Room
│   └── repository/    # Business logic & gamification processing
├── ui/
│   ├── activities/    # Activity controllers
│   └── adapters/      # List adapters for RecyclerView
├── utils/             # Helper classes (Currency, Notifications)
├── viewmodel/         # UI state management
└── worker/            # Background tasks
```

## ⚙️ Requirements

- **Android Studio** Hedgehog (2023.1.1) or newer.
- **JDK 17**
- **Min SDK:** 24 (Android 7.0)
- **Target SDK:** 34 (Android 14)

## 🏗 Build Instructions

1. Clone the repository.
2. Open the project in Android Studio.
3. Sync Gradle and run the `:app` module.

```bash
# Build via CLI
./gradlew assembleDebug
```

## 📜 License

This project is for educational purposes and personal use.

---
*Developed with ❤️ for better financial habits.*
