package com.thoughtworks.helloworld_view

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.helloworld_view.utils.FragmentUtils

class LanguageSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.language_selection_layout)
        modifyNavigation()
    }

    private fun modifyNavigation() {
        val androidButton = findViewById<Button>(R.id.button1)
        val javaButton = findViewById<Button>(R.id.button2)
        androidButton.setOnClickListener {
            FragmentUtils.replaceFragment(supportFragmentManager, R.id.fragment_container_info, AndroidInfoFragment())
        }

        javaButton.setOnClickListener {
            FragmentUtils.replaceFragment(supportFragmentManager, R.id.fragment_container_info, JavaInfoFragment())
        }
    }
}