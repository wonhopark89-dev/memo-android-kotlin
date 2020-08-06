package com.wonhopark.memo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(MemoEntity::class), version = 1) // 추가할 엔티티 목록 및 DB 버전
abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDAO(): MemoDAO //

    companion object {
        var INSTANCE: MemoDatabase? = null

        fun getInstance(context: Context): MemoDatabase? {
            if (INSTANCE == null) {
                synchronized(MemoDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MemoDatabase::class.java,
                        "memo.db"
                    )
                        .fallbackToDestructiveMigration() // 이전하고 데이터이스 버전이 달라지면 그냥 삭제하고 생성함
                        .build()
                }
            }
            return INSTANCE
        }
    }
}