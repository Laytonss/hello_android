package com.thoughtworks.helloworld_view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("LoginActivity Status", "onCreate状态")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        supportActionBar?.hide()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    override fun onStart() {
        super.onStart()
        Log.d("LoginActivity Status", "onStart状态")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LoginActivity Status", "onResume状态")
    }

    override fun onPause() {
        super.onPause()

        // 输出 onPause() 方法开始的信息
        Log.d("LoginActivity Status", "onPause状态")
    }

    override fun onStop() {
        super.onStop()
        // 输出 onStop() 方法开始的信息
        Log.d("LoginActivity Status", "onStop状态")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LoginActivity Status", "onDestroy状态")
    }
}