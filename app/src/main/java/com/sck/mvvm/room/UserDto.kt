package com.sck.mvvm.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDto {
    @Query("SELECT * from User")
    suspend fun getAll():List<User>

    @Query("select * from user where uid in (:userIds)")
    suspend fun loadAllByIds(userIds:IntArray):List<User>

    @Query("select * from user where name like :name limit 1")
    suspend fun findByName(name:String):User

    @Insert
    suspend fun insertAll(vararg users:User)

    @Delete
    suspend fun delete(user: User)
}