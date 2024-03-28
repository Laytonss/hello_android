package com.thoughtworks.helloworld_view

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import androidx.activity.result.contract.ActivityResultContract

class SelectContactContract : ActivityResultContract<Void, Uri?>() {
    override fun createIntent(context: Context, input: Void): Intent {
        return Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        if (resultCode == RESULT_OK) {
            return intent?.data
        }
        return null
    }
}