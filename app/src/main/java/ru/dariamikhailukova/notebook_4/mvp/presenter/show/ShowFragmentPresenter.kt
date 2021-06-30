package ru.dariamikhailukova.notebook_4.mvp.presenter.show

import android.text.TextUtils
import androidx.lifecycle.ViewModelProvider
import ru.dariamikhailukova.notebook_4.R
import ru.dariamikhailukova.notebook_4.data.Note
import ru.dariamikhailukova.notebook_4.data.NoteViewModel
import ru.dariamikhailukova.notebook_4.mvp.view.show.ShowFragment
import ru.dariamikhailukova.notebook_4.mvp.view.show.ShowView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ShowFragmentPresenter(_view: ShowFragment): ShowPresenter {
    private var view: ShowView = _view
    private var mNoteViewModel: NoteViewModel = ViewModelProvider(_view).get(NoteViewModel::class.java)

    init {
        view.initView()
    }

    //вызов неяного интента - отправки элемента бд
    override fun sendEmail(name: String, text: String, date: String) {
        if(inputCheck(name, text)){
            view.sendIntent(name, text)
        }else{
            view.showToast(R.string.fill_all)
        }

    }

    //обновление полей элемента бд
    override fun update(name: String, text: String) {
        if(inputCheck(name,text)){
            val updatedNote = Note(view.currentNoteId(), name, text, Date())

            mNoteViewModel.updateNote(updatedNote)
            view.showToast(R.string.update)
            view.returnToList()
        }else{
            view.showToast(R.string.fill_all)
        }
    }

    //удаление элемента бд
    override fun delete(currentNote: Note) {
        mNoteViewModel.deleteNote(currentNote)
        view.showToast(R.string.remove)
        view.returnToList()
    }

    //все ли строки заполнены
    override fun inputCheck(name: String, text: String): Boolean{
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(text))
    }

}