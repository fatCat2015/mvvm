package com.sck.mvvm.simpleStorage

interface SimpleLocalStorage {
    fun <T> get(key:String,defaultValue:T?):T?
    fun <T> getObject(key:String,type:Class<T>):T?
    fun put(key:String,value:Any?)
    fun put(valuePairs:Map<String,Any?>)
}