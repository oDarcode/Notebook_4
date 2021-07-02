package ru.dariamikhailukova.notebook_4.mvp.view.viewPager

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import ru.dariamikhailukova.notebook_4.R
import ru.dariamikhailukova.notebook_4.adapter.ViewPagerAdapter

import ru.dariamikhailukova.notebook_4.data.NoteViewModel
import ru.dariamikhailukova.notebook_4.databinding.FragmentStartBinding
import ru.dariamikhailukova.notebook_4.mvp.view.current.ViewPagerFragment

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNoteViewModel: NoteViewModel
    private val TAG = "NoteActivity"
    private val args by navArgs<StartFragmentArgs>()


    private lateinit var adapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)

        mNoteViewModel = ViewModelProvider(this).get(ru.dariamikhailukova.notebook_4.data.NoteViewModel::class.java)
        //binding.noteModel = mNoteViewModel
        //binding.lifecycleOwner = viewLifecycleOwner
        /*
        * <variable
            name="NoteModel"
            type="ru.dariamikhailukova.notebook_4.data.NoteViewModel"/>*/
        adapter = ViewPagerAdapter(this)
        viewPager = binding.viewPager
        viewPager.adapter = adapter


        mNoteViewModel.readAllData.observe(viewLifecycleOwner) { notes ->
            adapter.setData(notes)
        }

        //val viewPager = binding.viewPager
        //viewPager.adapter = adapter

        //binding.viewPager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        //mNoteViewModel.readAllData.observe(viewLifecycleOwner, { notes ->
         //   adapter.setData(notes)
        //binding.viewPager.adapter = ViewPagerAdapter(notes, childFragmentManager, lifecycle)
        //})
/*
        val adapter = ViewPager2Adapter()
        val viewPager = binding.viewPager
        viewPager.adapter = adapter

        mNoteViewModel.readAllData.observe(viewLifecycleOwner) { notes ->
            adapter.setData(notes)
        }
*/
        //пока выключу
        Handler().postDelayed({
            viewPager.setCurrentItem(args.pos, false)
        }, 100)

        //Log.d(TAG, viewPager.currentItem.toString())

        setHasOptionsMenu(true)

        return binding.root
    }



    //Создание меню
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_share_delete_menu, menu)
    }

    fun ViewPager2.findCurrentFragment(fragmentManager: FragmentManager): Fragment? {
        return fragmentManager.findFragmentByTag("f$currentItem")
    }

    fun ViewPager2.findFragmentAtPosition(
        fragmentManager: FragmentManager,
        position: Int
    ): Fragment? {
        return fragmentManager.findFragmentByTag("f$position")
    }


    //выбор элемента меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            val myFragment: ViewPagerFragment = binding.viewPager.findCurrentFragment(childFragmentManager) as ViewPagerFragment
            myFragment.deleteNote()
        }

        if(item.itemId == R.id.menu_share){
            val myFragment: ViewPagerFragment = binding.viewPager.findCurrentFragment(childFragmentManager) as ViewPagerFragment
            myFragment.sendTo()
            //presenter?.sendEmail(name, text, date)
            //Toast.makeText(requireContext(), "Hello", Toast.LENGTH_LONG).show()
        }

        if(item.itemId == R.id.menu_save){
            val myFragment: ViewPagerFragment = binding.viewPager.findCurrentFragment(childFragmentManager) as ViewPagerFragment
            myFragment.updateItem()

            Log.d(TAG, (myFragment).toString())

        }
        return super.onOptionsItemSelected(item)
    }


}