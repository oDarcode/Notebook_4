package ru.dariamikhailukova.notebook_4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.dariamikhailukova.notebook_4.R
import ru.dariamikhailukova.notebook_4.data.Note
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

//не использую
class ViewPager2Adapter : RecyclerView.Adapter<ViewPager2Adapter.MyViewHolder>() {

    private var noteList = emptyList<Note>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var name: EditText? = null
        var text: EditText? = null
        var date: TextView? = null

        init{
            name = itemView.findViewById(R.id.nameEditText)
            text = itemView.findViewById(R.id.textEditText)
            date = itemView.findViewById(R.id.dateTextView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_current, parent, false))
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.name?.setText(currentItem.name)
        holder.text?.setText(currentItem.text)
        holder.date?.text = getDate(currentItem.date)

    }

    private fun getDate(currentDate: Date): String {
        val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return  timeFormat.format(currentDate) + "   " + dateFormat.format(currentDate)
    }


    fun setData(note: List<Note>){
        this.noteList = note
        notifyDataSetChanged()
    }

}