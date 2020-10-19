package com.sck.mvvm.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sck.mvvm.appName
import com.sck.mvvm.application

@Database(entities = [User::class],version = appDatabaseVersion)
abstract class AppDataBase:RoomDatabase() {
    abstract fun userDao():UserDto

    companion object{
        val migration_1_to_2=object :Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table user add column height REAL not null default 0")
            }
        }
        val migration_2_to_3=object :Migration(2,3){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table user add column weight REAL not null default 0")
            }
        }
    }
}

const val appDatabaseVersion=3

val appDatabase:AppDataBase by lazy {
    Room.databaseBuilder(application,AppDataBase::class.java,"${appName}")
        .addMigrations(AppDataBase.migration_1_to_2,AppDataBase.migration_2_to_3)
        .build()
}