package com.diavolo.githubuserfinder.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.diavolo.githubuserfinder.data.local.dao.SearchHistoryDao
import com.diavolo.githubuserfinder.data.local.entity.SearchHistoryEntity

@Database(
    entities = [SearchHistoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao
    
    companion object {
        const val DATABASE_NAME = "github_user_finder_database"
    }
}
