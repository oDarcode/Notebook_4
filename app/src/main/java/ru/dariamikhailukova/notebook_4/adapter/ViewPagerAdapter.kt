package ru.dariamikhailukova.notebook_4.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.dariamikhailukova.notebook_4.data.Note
import ru.dariamikhailukova.notebook_4.mvp.view.current.NOTE
import ru.dariamikhailukova.notebook_4.mvp.view.current.ViewPagerFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private var notes: List<Note> = emptyList()

    override fun getItemCount(): Int = notes.size

    override fun createFragment(position: Int): Fragment {
        val fragment = ViewPagerFragment()
        fragment.arguments = Bundle().apply {
            putParcelable(NOTE, notes[position])
        }

        return fragment
    }

    fun setData(note: List<Note>){
        this.notes = note
        notifyDataSetChanged()
    }

}