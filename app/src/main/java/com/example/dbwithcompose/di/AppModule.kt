package com.example.dbwithcompose.di

import android.app.Application
import androidx.room.Room
import com.example.dbwithcompose.db.ListRepository
import com.example.dbwithcompose.db.ListRepositoryImpl
import com.example.dbwithcompose.db.MainDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideMainDatabase(app: Application): MainDataBase {
        return Room.databaseBuilder(
            app,
            MainDataBase::class.java,
            "list_db"
        ).build()
    }
    @Provides
    @Singleton
    fun provideRepository(db: MainDataBase) : ListRepository{
        return ListRepositoryImpl(db.getDao())
    }
}