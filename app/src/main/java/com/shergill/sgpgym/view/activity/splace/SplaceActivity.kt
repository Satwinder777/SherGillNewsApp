package com.shergill.sgpgym.view.activity.splace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.shergill.sgpgym.R
import com.shergill.sgpgym.databinding.ActivitySplaceBinding
import com.shergill.sgpgym.view.activity.MainActivity

class SplaceActivity : AppCompatActivity() {
    lateinit var binding:ActivitySplaceBinding
    lateinit var topAnimation: Animation
    lateinit var bottomAnimation: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplaceBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
       topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation)
       bottomAnimation =  AnimationUtils.loadAnimation(this,R.anim.bottom_animation)

        binding.textView.animation = bottomAnimation
        binding.textView2.animation = bottomAnimation
        binding.imageview.animation = topAnimation

        Handler().postDelayed({

//            Toast.makeText(this, "done success fully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        },2000)
    }
}