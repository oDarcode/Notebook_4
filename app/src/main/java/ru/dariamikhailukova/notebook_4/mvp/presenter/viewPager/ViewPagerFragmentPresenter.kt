package ru.dariamikhailukova.notebook_4.mvp.presenter.viewPager

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import ru.dariamikhailukova.notebook_4.data.Note
import ru.dariamikhailukova.notebook_4.data.NoteViewModel
import ru.dariamikhailukova.notebook_4.mvp.view.viewPager.ViewPager
import ru.dariamikhailukova.notebook_4.mvp.view.viewPager.ViewPagerFragment

class ViewPagerFragmentPresenter(_view: ViewPagerFragment): ViewPagerPresenter {
    private var view: ViewPager = _view
    private var mNoteViewModel: NoteViewModel = ViewModelProvider(_view).get(NoteViewModel::class.java)

    //считывание всех элементов бд
    override fun getAllData(): LiveData<List<Note>> {
        return mNoteViewModel.readAllData
    }
}