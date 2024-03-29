package com.thoughtworks.helloworld_view

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

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
            val androidInfoFragment = AndroidInfoFragment()
            val transaction2 = supportFragmentManager.beginTransaction()
            transaction2.add(R.id.fragment_container_info, androidInfoFragment)
            transaction2.commit()
        }

        javaButton.setOnClickListener {
            val javaInfoFragment = JavaInfoFragment()
            val transaction2 = supportFragmentManager.beginTransaction()
            transaction2.add(R.id.fragment_container_info, javaInfoFragment)
            transaction2.commit()
        }
    }
}