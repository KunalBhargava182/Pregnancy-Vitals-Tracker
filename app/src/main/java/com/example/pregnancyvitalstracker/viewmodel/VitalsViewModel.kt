package com.example.pregnancyvitalstracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pregnancyvitalstracker.data.db.VitalsDatabase
import com.example.pregnancyvitalstracker.data.model.Vitals
import com.example.pregnancyvitalstracker.repository.VitalsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VitalsViewModel(application: Application) : AndroidViewModel(application) {

    private val vitalsDao = VitalsDatabase.getDatabase(application).vitalsDao()
    private val repository = VitalsRepository(vitalsDao)

    val vitalsList: StateFlow<List<Vitals>> = repository.allVitals
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addVitals(vitals: Vitals) = viewModelScope.launch {
        repository.insertVitals(vitals)
    }
}
