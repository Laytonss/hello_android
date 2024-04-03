package com.thoughtworks.helloworld_view.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.helloworld_view.R

class SharePreferenceActivity : AppCompatActivity(R.layout.share_layout) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()

    }

    private fun initUi() {
        val sharedPref = this.getSharedPreferences(getString(R.string.share_preference_key), Context.MODE_PRIVATE)
        val isHintShown = sharedPref.getBoolean(getString(R.string.ishintshownKey), false)
        Log.d("isHintShown", "$isHintShown")
        initHintTextView(isHintShown)
        initHintButton(isHintShown, sharedPref)
    }

    private fun initHintTextView(isHintShown: Boolean) {
        val textView = findViewById<TextView>(R.id.hint_text_id)
        textView.text = if (isHintShown) {
            getString(R.string.SpHint2)
        } else {
            getString(R.string.SpHint1)
        }
    }

    private fun initHintButton(isHintShown: Boolean, sharedPref: SharedPreferences) {
        val button = findViewById<Button>(R.id.hint_button_id)
        button.visibility = if (isHintShown) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
        button.setOnClickListener {
            with(sharedPref.edit()) {
                putBoolean(getString(R.string.ishintshownKey), true)
                commit()
            }
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}