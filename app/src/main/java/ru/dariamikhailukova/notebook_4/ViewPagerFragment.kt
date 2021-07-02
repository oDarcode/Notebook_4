package ru.dariamikhailukova.notebook_4

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.TextView
import ru.dariamikhailukova.notebook_4.data.Note
import ru.dariamikhailukova.notebook_4.databinding.FragmentViewPagerBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

const val NOTE = "Note"
class ViewPagerFragment : Fragment() {


    private lateinit var binding: FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        Log.d(TAG, "i am working")
        // Toast.makeText(requireContext(), "i working", Toast.LENGTH_LONG).show()

        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(TAG) }?.apply {
            val note: Note? = getParcelable(NOTE)

            binding.nameEditText.setText("LOL")
            binding.textEditText.setText(note?.text)
            binding.dateTextView.text = note?.date?.let { getDate(it) }
        }
    }

    private fun getDate(currentDate: Date): String {
        val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return  timeFormat.format(currentDate) + "   " + dateFormat.format(currentDate)
    }

    companion object {
        private const val TAG = "NoteActivity"
        private const val SELECTED_POSITION = "selectedPosition"
        private const val NOTES_LIST = "notesList"
        fun newInstance(note: Note): ViewPagerFragment {
            return ViewPagerFragment().apply {
                /*arguments = Bundle().apply {
                    putParcelable(TAG, note)
                    //putString(TAG, note.name)
                }*/
                arguments?.apply {
                    val name: TextView = view!!.findViewById(R.id.nameEditText)
                    name.text = "fff"
                }
            }
        }
    }



}