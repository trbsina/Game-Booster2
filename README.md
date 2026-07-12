🚀 GameBooster: Advanced Android Performance Suite

**GameBooster** is a professional-grade optimization engine designed to provide an elite, lag-free gaming experience on Android. By combining deep system-level hardware monitoring, accessibility-driven automation, and low-latency network tuning, it ensures your device is fully optimized for peak performance.

---

## 🌟 Key Features

### 🏎️ Proactive Boosting Engine
*   **Smart Task Management:** Automatically identifies and hibernates resource-heavy background processes. It utilizes a "Smart Kill List" and a protected whitelist (System UI, Input Methods, etc.) to ensure device stability.
*   **Frame Lag Response:** The only booster that actively monitors the UI thread's frame-rendering times. If the `FrameMonitor` detects stuttering or dropped frames, the engine triggers a "Micro-Boost" to reclaim resources in real-time.
*   **Thermal Guard:** Real-time CPU and Battery temperature tracking. It categorizes heat levels (Fair, Serious, Critical) and can automatically scale back background activity to prevent thermal throttling and hardware damage.

### 📡 Network & Connectivity Turbo
*   **High-Performance WiFi Lock:** Acquires a specialized `WIFI_MODE_FULL_HIGH_PERF` lock to minimize packet loss and jitter during online competitive matches.
*   **DNS Turbo:** Automatically configures Private DNS to Cloudflare (1.1.1.1) for faster lookup times and reduced game-server latency.
*   **Sync Suspension:** Pauses Android’s Master Sync service during gameplay to prevent background data spikes from stealing bandwidth.

### 🤖 Intelligent Automation
*   **Zero-Touch Auto-Boost:** Leveraging the `GameBoosterAccessibilityService`, the app detects exactly when a user-defined game enters the foreground and triggers "Gaming Mode" optimizations instantly without user intervention.
*   **Persistent Monitoring:** A robust Foreground Service ensures that the system doesn't kill the booster engine under load, providing a persistent notification with live RAM and status updates.

### 📈 Gaming Analytics & Dashboard
*   **Session Tracking:** Automatically logs game duration, start/end times, and frequency of play.
*   **Hybrid Dashboard:** A sleek, responsive HTML5/JS interface rendered via a native WebView Bridge, providing a modern look at your system's health with real-time data visualization.

---

## 🏗️ Technical Architecture

The project is built with a modular, service-oriented architecture, leveraging **Kotlin Coroutines** and **StateFlow** for high-frequency hardware polling with minimal battery impact.

### 🧠 Core Components
1.  **`BoosterEngine`**: The central orchestrator coordinating monitors, optimization sequences (DND, DNS, Sync), and the task-killing logic.
2.  **`Monitors` (Hardware Layer)**:
    *   `RamMonitor`: Polls system memory state every 2 seconds.
    *   `ThermalMonitor`: Listens to system thermal events to manage heat profiles.
    *   `FrameMonitor`: Measures frame-to-frame latency in milliseconds using `Choreographer` callbacks.
3.  **`Bridge` (Hybrid UI Layer)**: A robust `JavascriptInterface` that bridges the native Android hardware APIs to the Web-based dashboard.

### 📂 Project Structure
GameBooster Architecture Overview
=================================

The project follows a Service-Oriented architecture with a Hybrid UI layer, designed for high-frequency hardware monitoring and automated system optimization.

1. UI Layer (Hybrid)
--------------------
- MainActivity: Acts as the container for the primary dashboard.
- WebView Dashboard: Uses HTML5/JS (located in assets/ui) for a modern, responsive interface.
- Bridge (JavascriptInterface): Facilitates bi-directional communication between the Dashboard (JS) and the native Android OS (Java/Kotlin).

2. Service Layer (Automation & Persistence)
-------------------------------------------
- BoosterForegroundService: Maintains a persistent presence to ensure the optimization engine stays active under system pressure. Manages the live notification stats.
- GameBoosterAccessibilityService: Monitors foreground application changes. Automatically triggers the "Boosting Engine" when a recognized game is launched.

3. Engine Layer (Core Logic)
----------------------------
- BoosterEngine: The central controller. Orchestrates task killing, network optimization (WiFi Locks, Private DNS), and system state changes (DND, Sync).
- SmartKillList: Logic for identifying non-critical background processes while protecting essential system services.

4. Monitoring Layer (Hardware Polling)
--------------------------------------
- RamMonitor: Tracks system-wide memory availability using StateFlow.
- ThermalMonitor: Monitors CPU and Battery temperature levels to prevent thermal throttling.
- FrameMonitor: Uses Choreographer callbacks to detect real-time frame lag and stuttering.

5. Data/Persistence Layer
-------------------------
- SharedPreferences: Stores user-defined kill lists, game profiles, and session history (JSON formatted).

### Skeleton
gamebooster2/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── assets/
│   │       │   └── ui/
│   │       │       └── dashboard.html         # Hybrid UI Dashboard
│   │       ├── java/com/gamebooster2/
│   │       │   ├── booster/
│   │       │   │   ├── BoosterEngine.java     # Core logic & Optimization
│   │       │   │   └── SmartKillList.java     # Process management logic
│   │       │   ├── monitor/
│   │       │   │   ├── FrameMonitor.java      # Lag & FPS tracking
│   │       │   │   ├── RamInfo.java           # RAM data model
│   │       │   │   ├── RamMonitor.java        # Memory tracking
│   │       │   │   ├── ThermalInfo.java       # Heat data model
│   │       │   │   ├── ThermalLevel.java      # Heat severity enums
│   │       │   │   └── ThermalMonitor.java    # Temperature tracking
│   │       │   ├── service/
│   │       │   │   ├── BoosterForegroundService.java      # Background persistence
│   │       │   │   └── GameBoosterAccessibilityService.java # Auto-boost automation
│   │       │   └── ui/
│   │       │       └── MainActivity.java      # App entry & WebView bridge
│   │       ├── res/                           # Layouts, drawables, and XML configs
│   │       └── AndroidManifest.xml            # App permissions & components
│   └── build.gradle                           # App-level build config
├── gradle/                                    # Gradle wrapper files
├── build.gradle                               # Project-level build config
├── settings.gradle                             # Module definitions
└── gradlew                                    # Gradle executable


---## 🔑 Permissions Breakdown

To provide deep system optimizations, GameBooster requires several elevated permissions:
*   `PACKAGE_USAGE_STATS`: To identify memory-hungry background apps.
*   `BIND_ACCESSIBILITY_SERVICE`: Required for the "Auto-Boost" feature to detect game launches.
*   `WRITE_SECURE_SETTINGS`: Used to optimize DNS and advanced network parameters.
*   `SYSTEM_ALERT_WINDOW`: For displaying performance overlays.
*   `ACCESS_NOTIFICATION_POLICY`: Used to enable "Do Not Disturb" mode automatically.

---

## 🚀 Getting Started

1.  **Build:** Clone the repository and build the project in Android Studio (supports Target SDK 34).
2.  **Permission Setup:**
    *   Launch the app and grant **Usage Access**.
    *   Enable the **GameBooster Accessibility Service** in System Settings for Auto-Boost.
    *   Grant **Overlay** and **DND** permissions via the dashboard prompts.
3.  **Optimize:** Add your favorite games to the library, hit "Launch," and the engine will handle the rest.

---

## 🛠️ Technology Stack
*   **Language:** Java & Kotlin
*   **Concurrency:** Kotlin Coroutines & Flow
*   **UI Framework:** Hybrid (Native + WebView Dashboard)
*   **Minimum SDK:** 26 (Android 8.0)
*   **Target SDK:** 34 (Android 14)

---
*Developed for gamers, by developers. Experience peak performance.* 🎮🔥
