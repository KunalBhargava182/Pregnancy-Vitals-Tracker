package com.example.pregnancyvitalstracker.repository

import com.example.pregnancyvitalstracker.data.db.VitalsDao
import com.example.pregnancyvitalstracker.data.model.Vitals
import kotlinx.coroutines.flow.Flow

class VitalsRepository(private val vitalsDao: VitalsDao) {

    val allVitals: Flow<List<Vitals>> = vitalsDao.getAllVitals()

    suspend fun insertVitals(vitals: Vitals) {
        vitalsDao.insertVitals(vitals)
    }
}
