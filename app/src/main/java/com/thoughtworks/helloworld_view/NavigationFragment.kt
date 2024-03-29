package com.thoughtworks.helloworld_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class NavigationFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_navigation, container, false)
        val button1 = view.findViewById<Button>(R.id.button1)
        val button2 = view.findViewById<Button>(R.id.button2)
        button1.setOnClickListener {
            val infoFragment = InfoFragment()
            val bundle = Bundle()
            bundle.putString("type", "1")
            infoFragment.arguments = bundle
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_info, infoFragment)
            transaction.commit()
        }
        button2.setOnClickListener {
            val infoFragment = InfoFragment()
            val bundle = Bundle()
            bundle.putString("type", "2")
            infoFragment.arguments = bundle
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_info, infoFragment)
            transaction.commit()
        }
        return view
    }
}