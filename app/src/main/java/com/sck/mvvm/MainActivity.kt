package com.sck.mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sck.mvvm.room.RoomActivity
import com.sck.mvvm.simpleStorage.SimpleStorageActivity

val TAG="sck220"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





    }

    fun aa(view: View){
        startActivity(Intent(this,RoomActivity::class.java))
    }

    fun bb(view: View){
        startActivity(Intent(this,RoomActivity::class.java))
    }


    fun cc(view: View){
        startActivity(Intent(this,SimpleStorageActivity::class.java))
    }
}

