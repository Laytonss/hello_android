package com.thoughtworks.helloworld_view.model

class Sender {
     var userName: String? = null
     var nick: String? = null
     var avatar: String? = null

    constructor()
    constructor(userName: String?, nick: String?, avatar: String?) {
        this.userName = userName
        this.nick = nick
        this.avatar = avatar
    }

    fun setUserName(userName: String?): Sender {
        this.userName = userName
        return this
    }

    fun setNick(nick: String?): Sender {
        this.nick = nick
        return this
    }

    fun setAvatar(avatar: String?): Sender {
        this.avatar = avatar
        return this
    }
}
