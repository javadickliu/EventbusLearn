package com.example.eventbuslearn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EventBus.getDefault().register(this@MainActivity)
        mainactivity_btn.setOnClickListener {
            startActivity(Intent(this@MainActivity,SubscribeActivity::class.java))
        }

    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    fun getEventBusMessage(eventBusTestBean: EventBusTestBean){
        Log.d("test","thread111111="+(Looper.getMainLooper()==Looper.myLooper()))
          mainactivity_tv.setText(eventBusTestBean.toString())
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    fun getEventBusMessage2(eventBusTestBean: EventBusTestBean){
        Log.d("test","thread222222="+(Looper.getMainLooper()==Looper.myLooper()))
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    fun getEventBusMessage3(intTest:Int){
        Log.d("test","thread33333="+(Looper.getMainLooper()==Looper.myLooper()))
    }
}
