package com.thoughtworks.helloworld_view.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.helloworld_view.R
import com.thoughtworks.helloworld_view.fragment.AndroidInfoFragment
import com.thoughtworks.helloworld_view.fragment.JavaInfoFragment
import com.thoughtworks.helloworld_view.utils.FragmentUtils

class LanguageSelectionActivity : AppCompatActivity(R.layout.language_selection_layout) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        FragmentUtils.replaceFragment(supportFragmentManager, R.id.fragment_container_info, AndroidInfoFragment())
    }
}