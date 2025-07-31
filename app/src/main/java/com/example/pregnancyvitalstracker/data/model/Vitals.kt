package com.example.pregnancyvitalstracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vitals_table")
data class Vitals(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val systolic: Int,
    val diastolic: Int,
    val heartRate: Float,
    val weight: Float,
    val babyKicks: Int,
    val timestamp: Long = System.currentTimeMillis()
)
