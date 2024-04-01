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
            // 将计数器加 10 次
            for (i in 0 until 10) {
                // 将计数器更新到 UI 线程
                count++
                publishProgress(count)
                // 等待 1 秒
                Thread.sleep(1000)
            }
            return null
        }

        @Deprecated("Deprecated in Java")
        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            // 更新文本视图
            button.text = values[0].toString()
        }

        @Deprecated("Deprecated in Java")
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            button.isEnabled = true
        }
    }
}