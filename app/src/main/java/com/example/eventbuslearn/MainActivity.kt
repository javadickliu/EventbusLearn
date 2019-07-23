package com.example.eventbuslearn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import android.graphics.Typeface
import android.text.style.StyleSpan
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val SpannableString = SpannableString("·超出服务区域归还车辆将收取一定的调度费"+"\n·超出服务区域骑行车辆将会播放报警语音")
//        val underlineSpan = UnderlineSpan()
//        val bold1 = StyleSpan(Typeface.BOLD)
//        val bold2 = StyleSpan(Typeface.BOLD)
//        SpannableString.setSpan(bold1, 17, 20, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        SpannableString.setSpan(bold2, 36, 40, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        textstring.text = SpannableString


        val parkingAreaString = SpannableString("·禁停区域内禁止还车" + "\n·禁停区域内禁止锁车")
        val parkingAreaBold1 = StyleSpan(Typeface.BOLD)
        val parkingAreaBold2 = StyleSpan(Typeface.BOLD)
        parkingAreaString.setSpan(parkingAreaBold1, 6, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        parkingAreaString.setSpan(parkingAreaBold2, 17, 21, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textstring.text = parkingAreaString

        Log.d("formatDateTest","formatDateTest="+formatDateTest("2019-07-13 23:59:59"))


        mainactivity_post_btn.setOnClickListener {
            EventBus.getDefault().post(EventBusTestBean("lidongxun",26))
          //  startActivity(Intent(this@MainActivity,SubscribeActivity::class.java))
        }
        mainactivity_subscribe_btn.setOnClickListener {
            EventBus.getDefault().register(this@MainActivity)
        }
        button_check.setOnClickListener {
            isLegalName(edit_name.text.toString())
        }
        countDownView.initTime(40000)
        countDownView.start()
        countDownView.setOnTimeCompleteListener {
            Toast.makeText(this,"倒计时结束",Toast.LENGTH_SHORT).show()
        }
    }
    @Subscribe(threadMode = ThreadMode.POSTING ,priority = 1)
    fun getEventBusMessage(eventBusTestBean: EventBusTestBean){
        Log.d("test","thread111111="+(Looper.getMainLooper()==Looper.myLooper()))
          mainactivity_tv.setText(eventBusTestBean.toString())
    }
    @Subscribe(threadMode = ThreadMode.POSTING,priority = 2)
    fun getEventBusMessage2(eventBusTestBean: EventBusTestBean){
        Log.d("test","thread222222="+(Looper.getMainLooper()==Looper.myLooper()))
        EventBus.getDefault().cancelEventDelivery(eventBusTestBean)
    }
    fun formatDateTest(before: String): String {
        val after: String
        try {
            val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                .parse(before)
            after = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
        } catch (e: ParseException) {
            return before
        }

        return after
    }
     fun isLegalName(name:String):Boolean{
         val regex=Regex("[\\u4e00-\\u9fa5]+")
      //   val result=regex.find(name)
         val result=regex.matches(name)
         Toast.makeText(this,"匹配结果:"+result,Toast.LENGTH_SHORT).show()
         return  false
//        if (name.contains("·") || name.contains("•")){
//            if (name.matches()){
//                return true
//            }else {
//                return false
//            }
//        }else {
//            if (name.matches("^[\\u4e00-\\u9fa5]+$")){
//                return true
//            }else {
//                return false;
//            }
//        }
    }

//    @Subscribe(threadMode = ThreadMode.POSTING)
//    fun getEventBusMessage3(intTest:Int){
//        Log.d("test","thread33333="+(Looper.getMainLooper()==Looper.myLooper()))
//    }
}
