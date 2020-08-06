package com.wonhopark.memo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo")
data class MemoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    var memo: String = "" // 기본값 설정
)