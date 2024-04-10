package com.thoughtworks.helloworld_view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.thoughtworks.helloworld_view.R

const val READ_CONTRACT_PERMISSION = 2

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        addButtons()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        requestPermissions(arrayOf(android.Manifest.permission.READ_CONTACTS), READ_CONTRACT_PERMISSION)
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
        modifyConstraintButton(buttons[0])
        modifyLoginButton(buttons[1])
        modifyPickButton(buttons[2])
        modifyFragmentButton(buttons[3])
        modifyRecycleViewButton(buttons[4])
        modifyThreadButton(buttons[5])
        modifySharePreferenceButton(buttons[6])
        modifyDataStoreButton(buttons[7])
        modifyComposeButton(buttons[8])
        buttons.forEach { linearLayout.addView(it) }
    }

    private fun modifyComposeButton(button: Button) {
        button.text = resources.getString(R.string.compose_button)
        button.setOnClickListener {
            startActivity(Intent(this, DataStoreActivity::class.java))
        }
    }

    private fun modifyDataStoreButton(button: Button) {
        button.text = resources.getString(R.string.data_store_button)
        button.setOnClickListener {
            startActivity(Intent(this, DataStoreActivity::class.java))
        }
    }

    private fun modifySharePreferenceButton(button: Button) {
        button.text = resources.getString(R.string.share_button)
        button.setOnClickListener {
            startActivity(Intent(this, SharePreferenceActivity::class.java))
        }
    }

    private fun modifyThreadButton(button: Button) {
        button.text = resources.getString(R.string.thread_button)
        button.setOnClickListener {
            startActivity(Intent(this, ThreadActivity::class.java))
        }
    }

    private fun modifyRecycleViewButton(button: Button) {
        button.text = resources.getString(R.string.recyclerView_button)
        button.setOnClickListener {
            startActivity(Intent(this, TweetsActivity::class.java))
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

    @SuppressLint("Range")
    private fun modifyPickButton(button: Button) {
        button.text = resources.getString(R.string.pick_button)
        val registerForActivityResult = registerForActivityResult(ActivityResultContracts.PickContact()) { contactUri ->
            if (contactUri != null) {
                // Get contact information
                val cursor = contentResolver.query(contactUri, null, null, null, null)
                if (cursor != null && cursor.moveToFirst()) {
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME))
                    val hasPhoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                    val numbers = mutableSetOf<String>()
                    if (Integer.parseInt(hasPhoneNumber) > 0) {
                        val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                        val phonesCursor =
                            contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null)
                        phonesCursor?.use {
                            while (phonesCursor.moveToNext()) {
                                val phoneNumber = phonesCursor.getString(phonesCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replace("-", "").replace(" ", "")
                                numbers.add(phoneNumber)
                            }
                        }
                    }
                    if (numbers.size == 0) numbers.add("No numbers for this contract")
                    Toast.makeText(this, "$name $numbers", Toast.LENGTH_LONG).show()
                    cursor.close()
                }
            }
        }
        button.setOnClickListener {
            registerForActivityResult.launch(null)
        }
    }

    private fun modifyFragmentButton(button: Button) {
        button.text = resources.getString(R.string.fragment_button)
        button.setOnClickListener {
            startActivity(Intent(this, LanguageSelectionActivity::class.java))
        }
    }
}

