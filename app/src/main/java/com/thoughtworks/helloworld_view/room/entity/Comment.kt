package com.thoughtworks.helloworld_view.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Comment(
    val content: String,
    val sender: Sender
)
