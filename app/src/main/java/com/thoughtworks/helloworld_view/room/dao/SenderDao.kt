package com.thoughtworks.helloworld_view.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.thoughtworks.helloworld_view.room.entity.Sender

@Dao
interface SenderDao {
    @Query("SELECT * FROM Sender")
    suspend fun getAll(): List<Sender>

    @Insert
    suspend fun insertALL(senderList: List<Sender>)
}