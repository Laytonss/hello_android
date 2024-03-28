package com.thoughtworks.helloworld_view

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val REQUEST_CODE_PICK_CONTACT = 1

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

    private fun modifyPickButton(button: Button) {
        button.text = resources.getString(R.string.pick_button)
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            startActivityForResult(intent, REQUEST_CODE_PICK_CONTACT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_CONTACT && resultCode == RESULT_OK) {
            val contactUri = data!!.data!!
            val cursor = contentResolver.query(contactUri, null, null, null, null)
            if (cursor != null && cursor.moveToFirst()) {
                val nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                val name = cursor.getString(nameIndex)
                val numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val number = cursor.getString(numberIndex)
                Toast.makeText(this, "$name $number", Toast.LENGTH_SHORT).show()
                cursor.close()
            }
        }
    }
}

// dimens.xml