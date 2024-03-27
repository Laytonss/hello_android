package com.thoughtworks.helloworld_view

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        addButtons()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun addButtons() {
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayoutForButtons)
        for (i in 1..14) {
            val button = Button(this)
            // button.text = resources.getString(resources.getIdentifier("button$i", "string", packageName)) 反射低效且不安全
            // button.text = resources.getString(R.array.buttons + i) 不知道为啥报错
            //button.text = resources.getStringArray(R.array.buttons)[i] 不应该用数组，item一多难道要一行行敲吗
            button.text = String.format(resources.getString(R.string.button_text), i)
            addStyleForButton(button)
            if (i == 1) {
                button.text = resources.getString(R.string.constraint_layout_button)
                addJumpToConstraintLayoutEvent(button)
            }
            if (i == 2) {
                button.text = resources.getString(R.string.login_button)
                addJumpToLoginEvent(button)
            }
            linearLayout.addView(button)
        }
    }

    private fun addJumpToLoginEvent(button: Button) {
        button.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun addStyleForButton(button: Button) {
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.topMargin = 16
        layoutParams.width = 650
        button.layoutParams = layoutParams
    }

    private fun addJumpToConstraintLayoutEvent(button: Button) {
        button.setOnClickListener {
            startActivity(Intent(this, ConstraintActivity::class.java))
        }
    }
}