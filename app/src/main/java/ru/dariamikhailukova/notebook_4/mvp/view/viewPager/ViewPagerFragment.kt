package ru.dariamikhailukova.notebook_4.mvp.view.viewPager

import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import ru.dariamikhailukova.notebook_4.R
import ru.dariamikhailukova.notebook_4.adapter.ViewPagerAdapter

import ru.dariamikhailukova.notebook_4.data.NoteViewModel
import ru.dariamikhailukova.notebook_4.databinding.FragmentPagerBinding
import ru.dariamikhailukova.notebook_4.mvp.presenter.list.ListFragmentPresenter
import ru.dariamikhailukova.notebook_4.mvp.presenter.viewPager.ViewPagerFragmentPresenter
import ru.dariamikhailukova.notebook_4.mvp.view.current.CurrentFragmentView

class ViewPagerFragment : Fragment(), ViewPager {
    private val args by navArgs<ViewPagerFragmentArgs>()
    private var _binding: FragmentPagerBinding? = null
    private val binding get() = _binding!!

    private var presenter: ViewPagerFragmentPresenter? = null
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPagerBinding.inflate(inflater, container, false)
        presenter = ViewPagerFragmentPresenter(this)
        setHasOptionsMenu(true)

        adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter

        val allData = presenter?.getAllData()
        allData?.observe(viewLifecycleOwner) { notes ->
            adapter.setData(notes)
        }

        //ужано выглядит, но работает... а без handler не работает =\
        Handler().postDelayed({
            binding.viewPager.setCurrentItem(args.pos, false)
        }, 100)

        return binding.root
    }

    override fun ViewPager2.findCurrentFragment(fragmentManager: FragmentManager): Fragment? {
        return fragmentManager.findFragmentByTag("f$currentItem")
    }

    //Создание меню
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_share_delete_menu, menu)
    }

    //выбор элемента меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            val myFragment: CurrentFragmentView = binding.viewPager.findCurrentFragment(childFragmentManager) as CurrentFragmentView
            myFragment.deleteNote()
        }

        if(item.itemId == R.id.menu_share){
            val myFragment: CurrentFragmentView = binding.viewPager.findCurrentFragment(childFragmentManager) as CurrentFragmentView
            myFragment.sendTo()
        }

        if(item.itemId == R.id.menu_save){
            val myFragment: CurrentFragmentView = binding.viewPager.findCurrentFragment(childFragmentManager) as CurrentFragmentView
            myFragment.updateItem()
            //Log.d(TAG, (myFragment).toString())

        }
        return super.onOptionsItemSelected(item)
    }
}