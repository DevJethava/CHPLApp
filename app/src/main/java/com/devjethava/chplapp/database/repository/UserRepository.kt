package com.devjethava.chplapp.database.repository

import com.devjethava.chplapp.database.dao.UserDao
import com.devjethava.chplapp.database.entity.UserEntity

class UserRepository(private val userDao: UserDao) {

    suspend fun insertDataAsync(userEntity: UserEntity) = userDao.insertUserData(userEntity)

    suspend fun getListAsync() = userDao.fetchUsersData()
}