package com.qdd.mvxdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_glide.*


class GlideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)
        loadImage()
        btn_glide.setOnClickListener {
            loadImage()
        }

    }

    private fun loadImage() {
        val url =
            "https://source.unsplash.com/random/800x600"
        Glide.with(this)
            .load(url)
//            .thumbnail( 0.5f )//缩略图
            .dontAnimate() //关闭动画
//            .override(50,50)//剪裁图片
            .placeholder(R.drawable.ic_launcher_foreground)//图片加载出来前，显示的图片
            .error(R.drawable.ic_launcher_background)//图片加载失败后，显示的图片
            .skipMemoryCache(true)//跳过内存缓存
            .diskCacheStrategy( DiskCacheStrategy.NONE )//禁用磁盘缓存
                //DiskCacheStrategy.NONE 什么都不缓存
                //DiskCacheStrategy.SOURCE 只缓存全尺寸图
                //DiskCacheStrategy.RESULT 只缓存最终的加载图
                //DiskCacheStrategy.ALL 缓存所有版本图（默认行为）
            .priority (Priority.HIGH )//优先级
                //Priority.LOW
                //Priority.NORMAL
                //Priority.HIGH
                //Priority.IMMEDIAT
            .into(img_glide)
    }

    //2.我与音乐的关系极其亲密，在所有的艺术里，音乐是最有感觉的，而感觉又是一个无形的东西，
    // 它最容易以最快的速度，闪电般地进入你心灵最中间的那个地方。音乐是充满着灵性的，
    // 和其他艺术的表现形式都不一样，而且它和任何艺术之间都有着“通感”。
    //冯骥才这段文字给人的重要启示是：
    //A.音乐在所有的艺术里是最有感觉的
    //B.音乐是充满着灵性的，和其他艺术的表现形式不一样
    //C.人必须有欣赏音乐的耳朵，培养自己的灵性和感觉
    //D.音乐和任何艺术之间都有着“通感”
}