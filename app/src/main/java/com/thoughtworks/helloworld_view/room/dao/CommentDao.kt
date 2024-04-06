package com.thoughtworks.helloworld_view.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.thoughtworks.helloworld_view.room.entity.Comment

@Dao
interface CommentDao {
    @Query("SELECT * FROM Comment")
    fun getAll(): List<Comment>

    @Insert
    fun insertALL(commentList: List<Comment>)
}