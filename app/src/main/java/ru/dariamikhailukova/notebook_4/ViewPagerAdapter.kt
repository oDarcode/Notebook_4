package ru.dariamikhailukova.notebook_4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.dariamikhailukova.notebook_4.data.Note


//не использую
class ViewPagerAdapter( fa: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fa, lifecycle) {

    private var notes: List<Note> = emptyList()

    override fun getItemCount(): Int = notes.size
    override fun createFragment(position: Int): Fragment {
        //val newFragment = ViewPagerFragment.newInstance(notes[position])
        val fragment = ViewPagerFragment()
        fragment.arguments = Bundle().apply {
            val GET = notes.size.toString()
            Log.d(GET, "i do it")
            putParcelable(NOTE, notes[position])
        }

        return fragment
    }

    fun setData(note: List<Note>){
        this.notes = note
        notifyDataSetChanged()
        //notifyDataSetChanged()
    }


}