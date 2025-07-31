package com.example.pregnancyvitalstracker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.pregnancyvitalstracker.ui.screens.MainScreen
import com.example.pregnancyvitalstracker.ui.theme.PregnancyVitalsTrackerTheme
import com.example.pregnancyvitalstracker.viewmodel.VitalsViewModel
import com.example.pregnancyvitalstracker.worker.ReminderWorker
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    private val PERMISSION_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // ✅ Create Notification Channel
        createNotificationChannel()

        // ✅ Ask Notification Permission (Android 13+)
        askNotificationPermission()

        // ✅ Schedule Reminder Notification
        scheduleVitalsReminder()

        setContent {
            PregnancyVitalsTrackerTheme {
                val viewModel: VitalsViewModel = viewModel()
                Scaffold { paddingValues ->
                    MainScreen(viewModel = viewModel, modifier = Modifier.padding(paddingValues))
                }

            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Vitals Reminders"
            val descriptionText = "Reminder to log pregnancy vitals every 5 hours"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("vitals_channel", name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val granted = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (!granted) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    private fun scheduleVitalsReminder() {
        val workRequest = PeriodicWorkRequestBuilder<ReminderWorker>(5, TimeUnit.HOURS).build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "VitalsReminder",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}
