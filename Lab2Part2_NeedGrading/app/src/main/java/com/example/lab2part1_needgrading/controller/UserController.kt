package com.example.lab2part1_needgrading.controller

import com.example.lab2part1_needgrading.model.UserModel

class UserController(private var userModel: UserModel) {
    fun setUserName(name: Any) {
        userModel.userName = name.toString()
    }

    fun setUserId(id: String) {
        userModel.userId = id
    }

    fun updateUser(userName: Any) {
        setUserName(userName)
    }

    val userName: String
        get() = userModel.userName

    val userId: String
        get() = userModel.userId
}
