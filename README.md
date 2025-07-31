# 🤰 Pregnancy Vitals Tracker

An Android app built with **Jetpack Compose**, allowing pregnant mothers to seamlessly log and track their daily health vitals throughout pregnancy. The app provides an intuitive UI and modern user experience, enabling quick data entry of blood pressure, heart rate, weight, and baby kicks.

Vitals are stored locally using **Room Database**, and the list updates in real-time using **StateFlow** for efficient state management. To encourage consistent health tracking, the app uses **WorkManager** to send gentle reminder notifications every 5 hours.

With clean design, live updates, offline persistence, and background reminders — this app serves as a reliable digital health companion for expectant mothers.


---

## 📱 Features

- 📝 Log vitals like:
  - Blood Pressure (Systolic/Diastolic)
  - Heart Rate
  - Weight (kg)
  - Baby Kicks Count
- 📋 View vitals history on the home screen
- 🕒 Auto-refresh list using StateFlow
- 🔔 Smart reminder notifications every 5 hours
- 🎨 Clean, modern UI inspired by maternity health apps
- ✅ All data stored locally using Room DB

---

## 🎯 Tech Stack

| Layer | Tech |
|-------|------|
| Language | Kotlin |
| UI | Jetpack Compose |
| Architecture | MVVM |
| State Management | StateFlow |
| Local DB | Room |
| Background Work | WorkManager |
| Theme | Material3 + Custom Colors |

---

## 📸 Screenshots

| Add Vitals Dialog | Vitals List |
|-------------------|-------------|
| ![Add Vitals Dialog](https://github.com/user-attachments/assets/2f8aace8-d852-4611-95e2-74e1ffe72f39) | ![Vitals List](https://github.com/user-attachments/assets/95ae1b74-86eb-4f3f-a18d-139438b78fcc)
 |

| Reminder Notification |
|------------------------|
| ![Notification_Log Screenshot](https://github.com/user-attachments/assets/442acf05-6960-406e-9c48-6848944b65be) |


---

## ⏰ Reminder System

- Every **5 hours**, a notification prompts the user:
  - **Title**: _"Time to log your vitals!"_
  - **Message**: _"Stay on top of your health. Please update your vitals now!"_
- Tapping the notification opens the app directly.

---

## 🛠 How It Works

1. User taps ➕ FAB to add new vitals
2. Vitals are stored in **Room DB**
3. `StateFlow` ensures live updates on the UI
4. `WorkManager` runs periodic notifications

---

## 🖼️ Icons Used

All icons used in the app are from [Google Fonts – Material Icons](https://fonts.google.com/icons):

These are officially provided by Google under the [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).


## 🚀 Getting Started

1. Clone the repo:
   ```bash
   git clone https://github.com/KunalBhargava182/PregnancyVitalsTracker.git
