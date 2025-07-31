package com.example.pregnancyvitalstracker.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pregnancyvitalstracker.data.model.Vitals

@Database(entities = [Vitals::class], version = 1, exportSchema = false)
abstract class VitalsDatabase : RoomDatabase() {

    abstract fun vitalsDao(): VitalsDao

    companion object {
        @Volatile
        private var INSTANCE: VitalsDatabase? = null

        fun getDatabase(context: Context): VitalsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VitalsDatabase::class.java,
                    "vitals_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
