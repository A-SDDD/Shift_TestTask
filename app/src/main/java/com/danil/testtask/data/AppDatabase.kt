package com.danil.testtask.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FullUserModel::class],
    version = 1
)
abstract class AppDatabase:RoomDatabase() {
    abstract val dao: AppDao
}