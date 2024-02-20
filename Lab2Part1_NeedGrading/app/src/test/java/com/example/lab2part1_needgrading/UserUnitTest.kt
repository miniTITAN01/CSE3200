package com.example.lab2part1_needgrading

import com.example.lab2part1_needgrading.controller.UserController
import com.example.lab2part1_needgrading.model.UserModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserUnitTest {

    private lateinit var userModel: UserModel
    private lateinit var userController: UserController

    @Before
    fun setUp() {
        // Initialize UserModel and UserController before each test
        userModel = UserModel(userId = "", userName = "")
        userController = UserController(userModel)
    }

    @Test
    fun testSetUserName_updatesUserNameCorrectly() {
        val testName = "John Doe"
        userController.setUserName(testName)
        assertEquals(testName, userController.userName)
    }

    @Test
    fun testSetUserId_updatesUserIdCorrectly() {
        val testId = "12345"
        userController.setUserId(testId)
        assertEquals(testId, userController.userId)
    }

    @Test
    fun testUpdateUser_updatesUserName() {
        val newName = "Jane Doe"
        userController.updateUser(newName)
        assertEquals(newName, userController.userName)
    }
}
