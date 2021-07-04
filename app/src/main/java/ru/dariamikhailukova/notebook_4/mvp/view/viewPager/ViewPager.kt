package ru.dariamikhailukova.notebook_4.mvp.view.viewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2

interface ViewPager {
    fun ViewPager2.findCurrentFragment(fragmentManager: FragmentManager): Fragment?
}