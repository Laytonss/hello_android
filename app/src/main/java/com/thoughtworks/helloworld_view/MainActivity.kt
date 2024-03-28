package com.thoughtworks.helloworld_view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val REQUEST_CODE_PICK_CONTACT = 1
const val READ_CONTRACT_PERMISSION = 2

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

    @SuppressLint("Range")
    private fun modifyPickButton(button: Button) {
        button.text = resources.getString(R.string.pick_button)
        val registerForActivityResult = registerForActivityResult(ActivityResultContracts.PickContact()) { contactUri ->
            if (contactUri != null) {
                // Get contact information
                val cursor = contentResolver.query(contactUri, null, null, null, null)
                cursor?.use {
                    if (it.moveToFirst()) {
                        val name = it.getString(it.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME))
                        val hasPhoneNumber = it.getString(it.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                        if (Integer.parseInt(hasPhoneNumber) > 0) {
                            val id = it.getString(it.getColumnIndex(ContactsContract.Contacts._ID))
                            val phonesCursor =
                                contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null)
                            val numbers = mutableSetOf<String>()
                            phonesCursor?.use {
                                while (phonesCursor.moveToNext()) {
                                    val phoneNumber = phonesCursor.getString(phonesCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replace("-", "").replace(" ", "")
                                    numbers.add(phoneNumber)
                                }
                                Toast.makeText(this, "$name $numbers", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            Toast.makeText(this, "$name - No numbers", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
        button.setOnClickListener {
            registerForActivityResult.launch(null)
        }
    }

    //老的api
//    @SuppressLint("Range")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_CODE_PICK_CONTACT && resultCode == RESULT_OK) {
//            val contactUri = data?.data ?: return
//            val cursor = contentResolver.query(contactUri, null, null, null, null)
//            cursor?.use {
//                if (it.moveToFirst()) {
//                    val name = it.getString(it.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME))
//                    val hasPhoneNumber = it.getString(it.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
//                    if (Integer.parseInt(hasPhoneNumber) > 0) {
//                        val id = it.getString(it.getColumnIndex(ContactsContract.Contacts._ID))
//                        val phonesCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null)
//                        val numbers = mutableSetOf<String>()
//                        phonesCursor?.use {
//                            while (phonesCursor.moveToNext()) {
//                                val phoneNumber = phonesCursor.getString(phonesCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replace("-", "").replace(" ", "")
//                                numbers.add(phoneNumber)
//                            }
//                            Toast.makeText(this@MainActivity, "$name $numbers", Toast.LENGTH_LONG).show()
//                        }
//                    } else {
//                        Toast.makeText(this@MainActivity, "$name - No numbers", Toast.LENGTH_LONG).show()
//                    }
//                }
//            }
//        }
//    }
}

