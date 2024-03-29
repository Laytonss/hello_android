package com.thoughtworks.helloworld_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class InfoFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_info, container, false)
        val textView = view.findViewById<TextView>(R.id.info_text)
        val bundle = arguments
        bundle?.let {
            val type = bundle.get("type")
            if ("1".equals(type)) {
                textView.text = "qwdqfwefwefwfwefwefwfwefew"
            } else if ("2".equals(type)) {
                textView.text = "uuuuuuuuuuuuuuuuuuuuuuuuuu"
            } else {
                textView.text = "init text"
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}