package com.thoughtworks.helloworld_view.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object FragmentUtils {
    fun replaceFragment(fragmentManager: FragmentManager, fragmentContainerId: Int, targetFragment: Fragment) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(fragmentContainerId, targetFragment)
        transaction.commit()
    }
}