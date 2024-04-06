package com.thoughtworks.helloworld_view.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.thoughtworks.helloworld_view.room.entity.Comment

@Dao
interface CommentDao {
    @Query("SELECT * FROM Comment")
    suspend fun getAll(): List<Comment>

    @Insert
    suspend fun insertALL(commentList: List<Comment>)
}