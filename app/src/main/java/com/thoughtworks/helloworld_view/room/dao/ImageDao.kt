package com.thoughtworks.helloworld_view.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.thoughtworks.helloworld_view.room.entity.Image

@Dao
interface ImageDao {
    @Query("SELECT * FROM Image")
    suspend fun getAll(): List<Image>

    @Insert
    suspend fun insertALL(imageList: List<Image>)
}