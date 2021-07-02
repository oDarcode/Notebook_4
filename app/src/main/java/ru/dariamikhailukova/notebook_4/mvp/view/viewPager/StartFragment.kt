package ru.dariamikhailukova.notebook_4.mvp.view.viewPager

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import ru.dariamikhailukova.notebook_4.R
import ru.dariamikhailukova.notebook_4.ViewPager2Adapter
import ru.dariamikhailukova.notebook_4.ViewPagerFragment
import ru.dariamikhailukova.notebook_4.data.NoteViewModel
import ru.dariamikhailukova.notebook_4.databinding.FragmentStartBinding

class StartFragment : Fragment(), viewPager {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNoteViewModel: NoteViewModel
    private val TAG = "NoteActivity"
    private val args by navArgs<StartFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)

        mNoteViewModel = ViewModelProvider(this).get(ru.dariamikhailukova.notebook_4.data.NoteViewModel::class.java)
        binding.noteModel = mNoteViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        //val adapter = ViewPagerAdapter( childFragmentManager, lifecycle)
        //val viewPager = binding.viewPager
        //viewPager.adapter = adapter

        //binding.viewPager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        //mNoteViewModel.readAllData.observe(viewLifecycleOwner, { notes ->
         //   adapter.setData(notes)
        //binding.viewPager.adapter = ViewPagerAdapter(notes, childFragmentManager, lifecycle)
        //})

        val adapter = ViewPager2Adapter()
        val viewPager = binding.viewPager
        viewPager.adapter = adapter

        mNoteViewModel.readAllData.observe(viewLifecycleOwner) { notes ->
            adapter.setData(notes)
        }

        //(viewPager[0] as RecyclerView).scrollToPosition(args.pos)
        Handler().postDelayed({
            viewPager.setCurrentItem(args.pos, false)
        }, 100)

        //Log.d(TAG, viewPager.currentItem.toString())

        setHasOptionsMenu(true)



        return binding.root




/*
        val adapter = ViewPager2Adapter()
        val viewPager = binding.viewPager
        viewPager.adapter = adapter


        mNoteViewModel.readAllData.observe(viewLifecycleOwner) { notes ->
            adapter.setData(notes)
        }*/


        //mNoteViewModel.readAllData.observe(viewLifecycleOwner) { notes ->
        //    binding.viewPager.adapter = ViewPagerAdapter(notes, childFragmentManager, lifecycle)
        //}



        //return binding.root


        /*
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        binding.noteModel = mNoteViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        mNoteViewModel.readAllData.observe(viewLifecycleOwner) { notes ->
            if (notes == null || notes.isEmpty())
                //findNavController().navigate(MyCitiesFragmentDirections.actionMyCitiesFragmentToCitySearchActivity())
            else
                binding.viewPager.adapter = ViewPagerAdapter(notes, childFragmentManager, lifecycle)
        }
        return binding.root*/
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
            //deleteNote()
        }

        if(item.itemId == R.id.menu_share){
            //presenter?.sendEmail(name, text, date)
            Toast.makeText(requireContext(), "Hello", Toast.LENGTH_LONG).show()
        }

        if(item.itemId == R.id.menu_save){


            //childFragmentManager.beginTransaction().replace(R.id.viewPager, ViewPagerFragment(), "SOMETAG").commit()
            // Now later we can lookup the fragment by tag
            val page = childFragmentManager.findFragmentByTag("android:switcher:" + R.id.viewPager.toString() + ":" + binding.viewPager.currentItem)
            //binding.viewPager.currentItem

            //val fragmentDemo: ViewPagerFragment= childFragmentManager.findFragmentByTag("SOMETAG") as ViewPagerFragment

            //val myFragment = childFragmentManager.findFragmentById(R.id.viewPager)
            //getSupportFragmentManager().executePendingTranscation();
            //updateItem()
            //val myFragment = childFragmentManager.findFragmentByTag("3" ) //+ binding.viewPager.currentItem
            //val myFragment = binding.viewPager.findCurrentFragment(childFragmentManager)
            //val myFragment2 = binding.viewPager.findFragmentAtPosition(childFragmentManager, 3)
            Log.d(TAG, (R.id.viewPager.toString() + ":" + binding.viewPager.currentItem).toString())
        }
        return super.onOptionsItemSelected(item)
    }


}