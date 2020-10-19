package com.sck.mvvm.room

import androidx.room.*

@Entity(tableName = "user")
class User {
    @PrimaryKey(autoGenerate = true)
    var uid:Int=0

    var name:String?=null

    @ColumnInfo(name="avatar")
    var touXiang:String?=null

    @Ignore
    var token:String?=null

    @Embedded
    var address:Address?=null

    var height=175.0F
    var weight=175.0F

}


class Address(
    val country:String,
    val province:String,
    val city:String,
)