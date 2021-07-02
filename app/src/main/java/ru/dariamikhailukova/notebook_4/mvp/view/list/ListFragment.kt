package ru.dariamikhailukova.notebook_4.mvp.view.list

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dariamikhailukova.notebook_4.R
import ru.dariamikhailukova.notebook_4.adapter.ListAdapter
import ru.dariamikhailukova.notebook_4.databinding.FragmentListBinding
import ru.dariamikhailukova.notebook_4.mvp.presenter.list.ListFragmentPresenter

class ListFragment : Fragment(), ListView {
    private var presenter: ListFragmentPresenter? = null
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        presenter = ListFragmentPresenter(this)
        setHasOptionsMenu(true)

        val adapter = ListAdapter()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val allData = presenter?.getAllData()
        allData?.observe(viewLifecycleOwner, { note ->
            adapter.setData(note)
        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return binding.root
    }

    //Создание меню
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    //выбор элемента меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllNotes()
        }

        if(item.itemId == R.id.menu_about){
            findNavController().navigate(R.id.action_listFragment_to_startFragment)
            //val intent = Intent(activity, AboutActivity::class.java)
            //startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    //удаление всех элеметов бд
    override fun deleteAllNotes() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            presenter?.deleteAll()
        }

        builder.setNegativeButton("No"){_,_->}
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure?")
        builder.create().show()
    }

    //вывод Toast
    override fun showToast(text: Int) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}