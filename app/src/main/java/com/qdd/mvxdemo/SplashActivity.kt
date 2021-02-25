package com.qdd.mvxdemo

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_glide.*
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        initView()
    }

    private fun initView() {
        val url =
            "https://source.unsplash.com/random"
        Glide.with(this)
            .load(url)
            .into(img_start)
//        img_start.setImageResource(R.mipmap.ic_launcher)
        val scaleAnim = ScaleAnimation(
                0.2f,
                0.6f,
                0.2f,
                0.6f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
        )
        scaleAnim.fillAfter = true
        scaleAnim.duration = 5000
        scaleAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                //在这里做一些初始化的操作
                //跳转到指定的Activity
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        img_start.startAnimation(scaleAnim)
    }
}