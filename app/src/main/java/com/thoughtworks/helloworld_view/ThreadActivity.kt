package com.thoughtworks.helloworld_view

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ThreadActivity : AppCompatActivity() {

    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.thread_layout)
        button = findViewById(R.id.thread_button)
        button.text = getString(R.string.thread_button_start_hint)
        button.setOnClickListener {
            CountAsyncTask().execute()
        }
    }

    private inner class CountAsyncTask : AsyncTask<Void, Int, Void>() {

        private var count = 0

        @Deprecated("Deprecated in Java")
        override fun onPreExecute() {
            super.onPreExecute()
            button.isEnabled = false
        }


        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg params: Void?): Void? {
            for (i in 0 until 10) {
                count++
                publishProgress(count)
                Thread.sleep(1000)
            }
            return null
        }

        @Deprecated("Deprecated in Java")
        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            button.text = values[0].toString()
        }

        @Deprecated("Deprecated in Java")
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            button.text = getString(R.string.thread_button_end_hint)
            button.isEnabled = true
        }
    }
}