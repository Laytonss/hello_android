package com.thoughtworks.helloworld_view.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.preferences.core.edit
import com.thoughtworks.helloworld_view.R
import com.thoughtworks.helloworld_view.dataStore.dataStore
import com.thoughtworks.helloworld_view.dataStore.isHintShownKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DataStoreActivity : AppCompatActivity(R.layout.activity_data_store_laytout) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        initUi()
    }

    private fun initUi() {
        CoroutineScope(Dispatchers.IO).launch {
            val isHintShown = dataStore.data.first()[isHintShownKey] ?: false
            Log.d("isHintShown", "$isHintShown")
            initHintTextView(isHintShown)
            initHintButton(isHintShown)
        }
    }

    private fun initHintTextView(isHintShown: Boolean) {
        val textView = findViewById<TextView>(R.id.hint_text_id)
        textView.text = if (isHintShown) {
            getString(R.string.SpHint2)
        } else {
            getString(R.string.SpHint1)
        }
    }

    private fun initHintButton(isHintShown: Boolean) {
        val button = findViewById<Button>(R.id.hint_button_id)
        button.visibility = if (isHintShown) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
        button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                dataStore.edit { preferences ->
                    preferences[isHintShownKey] = true
                }
            }
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}