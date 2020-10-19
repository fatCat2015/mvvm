package com.sck.mvvm

import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.sck.base.Demo
import com.sck.mvvm.room.RoomActivity
import kotlinx.android.synthetic.main.activity_main.*

val TAG="sck220"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)







    }

    fun aa(view: View){
        startActivity(Intent(this,RoomActivity::class.java))
    }
}

