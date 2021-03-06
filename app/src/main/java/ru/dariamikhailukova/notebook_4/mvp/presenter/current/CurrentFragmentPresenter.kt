package ru.dariamikhailukova.notebook_4.mvp.presenter.current

import android.text.TextUtils
import androidx.lifecycle.ViewModelProvider
import ru.dariamikhailukova.notebook_4.R
import ru.dariamikhailukova.notebook_4.data.Note
import ru.dariamikhailukova.notebook_4.data.NoteViewModel
import ru.dariamikhailukova.notebook_4.mvp.view.current.CurrentView
import ru.dariamikhailukova.notebook_4.mvp.view.current.CurrentFragmentView
import java.util.*

class CurrentFragmentPresenter(_view: CurrentFragmentView): CurrentPresenter {
    private var view: CurrentView = _view
    private var mNoteViewModel: NoteViewModel = ViewModelProvider(_view).get(NoteViewModel::class.java)

    override fun sendEmail(name: String, text: String, date: String) {
        if(inputCheck(name, text)){
            view.sendIntent(name, text)
        }else{
            view.showToast(R.string.fill_all)
        }
    }

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

    override fun delete(currentNote: Note) {
        mNoteViewModel.deleteNote(currentNote)
        view.showToast(R.string.remove)
        view.returnToList()
    }

    override fun inputCheck(name: String, text: String): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(text))
    }

}