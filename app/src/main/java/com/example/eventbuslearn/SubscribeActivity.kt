package com.example.eventbuslearn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.greenrobot.eventbus.EventBus

class SubscribeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscribe)
   //     EventBus.getDefault().post(EventBusTestBean("lidongxun",26))
        EventBus.getDefault().post(Int)
    }
}