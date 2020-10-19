package com.sck.mvvm.simpleStorage

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import android.util.Log
import com.sck.mvvm.appName
import com.sck.mvvm.application
import java.io.*

class SharedPreferencesStorage:SimpleLocalStorage {


    private val sharedPreferences:SharedPreferences by lazy {
        application.getSharedPreferences(appName,Context.MODE_PRIVATE)
    }

    override fun <T> get(key: String, defaultValue: T?): T? {
        when(defaultValue){
            is Int ->{
                return sharedPreferences.getInt(key,defaultValue) as? T?
            }
            is Long ->{
                return sharedPreferences.getLong(key,defaultValue) as? T?
            }
            is Float ->{
                return sharedPreferences.getFloat(key,defaultValue) as? T?
            }
            is Boolean ->{
                return sharedPreferences.getBoolean(key,defaultValue) as? T?
            }
            is String ->{
                return sharedPreferences.getString(key,defaultValue) as? T?
            }
            is Set<*>->{
                return sharedPreferences.getStringSet(key,defaultValue as? Set<String>) as? T?
            }

        }
        return null
    }

    override fun <T> getObject(key: String, type: Class<T>): T? {
        if(type is Serializable){
            val base64Str=get(key,"")
            val byteArray=Base64.decode(base64Str,Base64.DEFAULT)
            return ObjectInputStream(ByteArrayInputStream(byteArray)).use {
                it.readObject()
            } as? T?
        }
        return null
    }

    override fun put(key: String, value: Any?) {
        val editor=sharedPreferences.edit()
        _putValue(key,value,editor)
        editor.apply()
    }

    override fun put(valuePairs: Map<String, Any?>) {
        val editor=sharedPreferences.edit()
        valuePairs.forEach{
            _putValue(it.key,it.value,editor)
        }
        editor.apply()
    }

    private fun _putValue(key:String,value:Any?,editor:SharedPreferences.Editor){
        when(value){
            is Int ->{
                editor.putInt(key,value)
            }
            is Long ->{
                editor.putLong(key,value)
            }
            is Float ->{
                editor.putFloat(key,value)
            }
            is Boolean ->{
                editor.putBoolean(key,value)
            }
            is String ->{
                editor.putString(key,value)
            }
            is Set<*> ->{
                editor.putStringSet(key,value as? Set<String>)  //泛型不是String类型时 value as? Set<String>值为null
            }
            is Serializable ->{
                ByteArrayOutputStream().also { byteArrayOutputStream ->
                    ObjectOutputStream(byteArrayOutputStream).use { objectOutputStream ->
                        objectOutputStream.writeObject(value)
                        objectOutputStream.flush()
                        put(key,Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT))
                    }
                }
            }
        }
    }



}
