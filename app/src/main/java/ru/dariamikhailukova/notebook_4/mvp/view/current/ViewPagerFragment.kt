package ru.dariamikhailukova.notebook_4.mvp.view.current

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import ru.dariamikhailukova.notebook_4.R
import ru.dariamikhailukova.notebook_4.data.Note
import ru.dariamikhailukova.notebook_4.databinding.FragmentViewPagerBinding
import ru.dariamikhailukova.notebook_4.mvp.presenter.current.CurrentFragmentPresenter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


const val NOTE = "Note"
class ViewPagerFragment : Fragment(), CurrentView {
    private var presenter: CurrentFragmentPresenter? = null
    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    private var id: Long = 0
    private lateinit var name: String
    private lateinit var text: String
    private lateinit var date: String

    lateinit var note: Note

    //private lateinit var binding: FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)

        presenter = CurrentFragmentPresenter(this)

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(NOTE) }?.apply {
            note = this.getParcelable(NOTE)!!

            id = note.id
            name = note.name
            text = note.text
            date = getDate(note.date)

            binding.nameEditText.setText(name)
            binding.textEditText.setText(text)
            binding.dateTextView.text = date

        }
    }




    override fun getDate(currentDate: Date): String {
        val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return  timeFormat.format(currentDate) + "   " + dateFormat.format(currentDate)
    }

    override fun updateItem() {
        name = binding.nameEditText.text.toString()
        text = binding.textEditText.text.toString()

        presenter?.update(name, text)
    }

    override fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
           presenter?.delete(note)
        }

        builder.setNegativeButton("No"){_,_->}
        builder.setTitle("Delete ${name}?")
        builder.setMessage("Are you sure?")
        builder.create().show()
    }

    override fun showToast(text: Int) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun sendIntent(name: String, text: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_SUBJECT, name)
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, ""))
    }

    override fun returnToList() {
        findNavController().navigate(R.id.action_startFragment_to_listFragment)
    }

    override fun currentNoteId(): Long {
        return id
    }

    override fun sendTo(){
        presenter?.sendEmail(name, text, date)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}