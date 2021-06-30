package ru.dariamikhailukova.notebook_4.mvp.presenter.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import ru.dariamikhailukova.notebook_4.R
import ru.dariamikhailukova.notebook_4.data.Note
import ru.dariamikhailukova.notebook_4.data.NoteViewModel
import ru.dariamikhailukova.notebook_4.mvp.view.list.ListFragment
import ru.dariamikhailukova.notebook_4.mvp.view.list.ListView

class ListFragmentPresenter(_view: ListFragment): ListPresenter {
    private var view: ListView = _view
    private var mNoteViewModel: NoteViewModel = ViewModelProvider(_view).get(NoteViewModel::class.java)

    //считывание всех элементов бд
    override fun getAllData(): LiveData<List<Note>> {
        return mNoteViewModel.readAllData
    }

    //удаление всех элементов бд
    override fun deleteAll(){
        mNoteViewModel.deleteAllNotes()
        view.showToast(R.string.remove_all)
    }
}