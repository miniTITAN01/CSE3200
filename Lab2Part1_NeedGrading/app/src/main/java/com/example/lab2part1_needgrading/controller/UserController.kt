package com.example.lab2part1_needgrading.controller

import com.example.lab2part1_needgrading.model.UserModel

class UserController(private val userModel: UserModel) {
    fun setUserName(name: String) {
        userModel.userName = name
    }

    fun setUserId(id: String) {
        userModel.userId = id
    }

    val userName: String
        get() = userModel.userName

    val userId: String
        get() = userModel.userId
}
