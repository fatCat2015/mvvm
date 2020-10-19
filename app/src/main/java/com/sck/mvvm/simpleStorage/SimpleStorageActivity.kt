package com.sck.mvvm.simpleStorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.sck.mvvm.R
import java.io.Serializable

class SimpleStorageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_storage_actviity)
    }

    fun put(view: View){
        val contact=Contact("sck","15801958366")
        val set= setOf("a","b","c")
        simpleLocalStorage.put(mapOf("a" to contact,"b" to set))
    }

    fun get(view: View){
        val contact=simpleLocalStorage.getObject("a",Contact::class.java)
        val set=simpleLocalStorage.get<Set<String>>("b", setOf())
        Log.i("sck220", "get: ${contact}  ${set}")
    }
}


data class Contact(
    val name:String,
    val mobile:String,
):Serializable