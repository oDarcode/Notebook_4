package ru.dariamikhailukova.notebook_4.mvp.view.show

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.dariamikhailukova.notebook_4.R
import ru.dariamikhailukova.notebook_4.databinding.FragmentShowBinding
import ru.dariamikhailukova.notebook_4.mvp.presenter.show.ShowFragmentPresenter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ShowFragment : Fragment(), ShowView {
    /*private var presenter: ShowFragmentPresenter? = null
    private val args by navArgs<ShowFragmentArgs>()

    private var _binding: FragmentShowBinding? = null
    private val binding get() = _binding!!

    private lateinit var name: String
    private lateinit var text: String
    private lateinit var date: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowBinding.inflate(inflater, container, false)
        presenter = ShowFragmentPresenter(this)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun initView(){
        name = args.currentNote.name
        text = args.currentNote.text
        date = getDate(args.currentNote.date)

        binding.nameEditText.setText(name)
        binding.textEditText.setText(text)
        binding.dateTextView.text = date
    }

    override fun getDate(currentDate: Date): String {
        val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return  timeFormat.format(currentDate) + "   " + dateFormat.format(currentDate)
    }

    //Создание меню
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_share_delete_menu, menu)
    }

    //выбор элемента меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteNote()
        }

        if(item.itemId == R.id.menu_share){
            presenter?.sendEmail(name, text, date)
        }

        if(item.itemId == R.id.menu_save){
            updateItem()
        }
        return super.onOptionsItemSelected(item)
    }



    //обновление полей элемента бд
    override fun updateItem(){
        name = binding.nameEditText.text.toString()
        text = binding.textEditText.text.toString()
        //date = binding.updateTextDate.text.toString()
        //date = Integer.parseInt(binding.updateTextDate.text.toString())

        presenter?.update(name, text)
    }

    //удаление элемета бд
    override fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            presenter?.delete(args.currentNote)
        }

        builder.setNegativeButton("No"){_,_->}
        builder.setTitle("Delete ${args.currentNote.name}?")
        builder.setMessage("Are you sure?")
        builder.create().show()
    }

    //вывод Toast
    override fun showToast(text: Int) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    //отправка неявного интента
    override fun sendIntent(name: String, text: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_SUBJECT, name)
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, ""))
    }

    //переход к фрагменту list
    override fun returnToList() {
        findNavController().navigate(R.id.action_showFragment_to_listFragment)

    }

    //возвращает id выбранного элемента бд
    override fun currentNoteId(): Long {
        return args.currentNote.id
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
*/
}