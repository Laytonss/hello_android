package com.thoughtworks.helloworld_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LanguageSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.language_selection_layout)
        addFragment()
    }

    private fun addFragment() {
        val navigationFragment = NavigationFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container_navigation, navigationFragment)
        transaction.commit()

        val infoFragment = InfoFragment()
        val transaction2 = supportFragmentManager.beginTransaction()
        transaction2.add(R.id.fragment_container_info, infoFragment)
        transaction2.commit()
    }
}