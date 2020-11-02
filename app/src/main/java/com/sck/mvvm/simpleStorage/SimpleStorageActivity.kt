package com.sck.mvvm.simpleStorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.sck.mvvm.R
import com.sck.mvvm.simpleLocalStorage
import java.io.Serializable

class SimpleStorageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_storage_actviity)
    }

    fun put(view: View){
        val contact=Contact("sck","15801958366")
        simpleLocalStorage.put(mapOf("a" to contact))
    }

    fun get(view: View){
        val contact=simpleLocalStorage.getObject("a",Contact::class.java)
        Log.i("sck220", "get: ${contact} ")
    }
}


data class Contact(
    val name:String,
    val mobile:String,
):Serializable