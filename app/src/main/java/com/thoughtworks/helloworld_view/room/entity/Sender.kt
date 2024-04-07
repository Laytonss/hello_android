package com.thoughtworks.helloworld_view.room.entity

import androidx.room.PrimaryKey


data class Sender (
    val userName: String? = null,
    val nick: String? = null,
    val avatar: String? = null,
)
