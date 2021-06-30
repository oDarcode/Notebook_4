package ru.dariamikhailukova.notebook_4.mvp.presenter.add

import android.text.TextUtils
import androidx.lifecycle.ViewModelProvider
import ru.dariamikhailukova.notebook_4.R
import ru.dariamikhailukova.notebook_4.data.Note
import ru.dariamikhailukova.notebook_4.data.NoteViewModel
import ru.dariamikhailukova.notebook_4.mvp.view.add.AddFragment
import ru.dariamikhailukova.notebook_4.mvp.view.add.AddView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddFragmentPresenter(_view: AddFragment): AddPresenter {
    private var view: AddView = _view
    private var mNoteViewModel: NoteViewModel = ViewModelProvider(_view).get(NoteViewModel::class.java)

    //добавление нового элемента в бд
    override fun insertDataToDatabase(name: String, text: String) {
        if (inputCheck(name, text)){
            val note = Note(0, name, text, Date())

            mNoteViewModel.addNote(note)
            view.showToast(R.string.successfully)
            view.returnToList()
        }else{
            view.showToast(R.string.fill_all)
        }
    }

    //все ли строки заполнены
    override fun inputCheck(name: String, text: String): Boolean{
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(text))
    }

    //возвращает текущую дату и время
    /*override fun getDate(): Date {
        //val sdf = getDateTimeInstance()
        //return sdf.format(Date())
        //System.out.println(" C DATE is  "+currentDate)
        val currentDate = Date()
        val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        //return timeFormat.format(currentDate) + " " + dateFormat.format(currentDate)
        //return currentDate.toString() + " " + currentDate.time.toString()
        return currentDate
    }*/
}