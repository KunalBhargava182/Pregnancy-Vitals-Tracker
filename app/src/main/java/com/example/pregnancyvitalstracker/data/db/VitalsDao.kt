package com.example.pregnancyvitalstracker.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pregnancyvitalstracker.data.model.Vitals
import kotlinx.coroutines.flow.Flow

@Dao
interface VitalsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVitals(vitals: Vitals)

    @Query("SELECT * FROM vitals_table ORDER BY timestamp DESC")
    fun getAllVitals(): Flow<List<Vitals>>
}
