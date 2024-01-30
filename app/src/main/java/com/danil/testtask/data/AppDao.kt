package com.danil.testtask.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem (item: List<FullUserModel>)

    @Delete
    suspend fun delete (item: List<FullUserModel>)

    @Query("SELECT * FROM full_users")
    fun getAllFlow(): Flow<List<FullUserModel>>


}