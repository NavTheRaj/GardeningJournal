package com.roman.gardeningjournal.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.miu.gardeningjournal.data.model.Plant

@Database(entities = [Plant::class], version = 1, exportSchema = false)
abstract class PlantDatabase : RoomDatabase() {
    abstract val plantDao: PlantDao
}
