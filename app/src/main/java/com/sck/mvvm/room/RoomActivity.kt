package com.sck.mvvm.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.sck.mvvm.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class RoomActivity : AppCompatActivity(),CoroutineScope by MainScope() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
    }

    fun a(view: View){
        launch {
            val list= appDatabase.userDao().getAll()
            Toast.makeText(this@RoomActivity, "${list.size}", Toast.LENGTH_SHORT).show()
        }
    }

    fun b(view: View){
        launch {
            appDatabase.userDao().insertAll(User().apply {
                name="sck"
            })
            Toast.makeText(this@RoomActivity, "插入成功", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}