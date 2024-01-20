package com.miu.gardeningjournal.di

import android.app.Application
import androidx.room.Room
import com.miu.gardeningjournal.data.repositories.PlantRepository
import com.miu.gardeningjournal.data.repositories.PlantRepositoryImpl
import com.roman.gardeningjournal.data.PlantDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): PlantDatabase {
        return Room.databaseBuilder(
            application,
            PlantDatabase::class.java,
            "plant_db"
        ).build()
    }

    @Singleton
    @Provides
    fun providePlantRepository(db: PlantDatabase): PlantRepository {
        return PlantRepositoryImpl(db.plantDao)
    }
}