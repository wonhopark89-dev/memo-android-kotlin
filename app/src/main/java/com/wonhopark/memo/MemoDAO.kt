package com.wonhopark.memo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface MemoDAO {

    @Insert(onConflict = REPLACE) // 키가 겹칠때
    fun insert(memo: MemoEntity)

    @Query("SELECT * FROM memo")
    fun getAll(): List<MemoEntity>

    @Delete
    fun delete(memo: MemoEntity)
}