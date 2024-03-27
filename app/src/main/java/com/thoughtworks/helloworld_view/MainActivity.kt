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
        val buttons = ArrayList<Button>()
        for (i in 1..14) {
            val button = Button(this)
            button.text = String.format(resources.getString(R.string.button_text), i)
            addStyleForButton(button)
            buttons.add(button)
        }
        modifyConstraintButton(buttons[1])
        modifyLoginButton(buttons[2])
        buttons.forEach { linearLayout.addView(it) }
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

    private fun modifyLoginButton(button: Button) {
        button.text = resources.getString(R.string.login_button)
        button.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun modifyConstraintButton(button: Button) {
        button.text = resources.getString(R.string.constraint_layout_button)
        button.setOnClickListener {
            startActivity(Intent(this, ConstraintActivity::class.java))
        }
    }
}