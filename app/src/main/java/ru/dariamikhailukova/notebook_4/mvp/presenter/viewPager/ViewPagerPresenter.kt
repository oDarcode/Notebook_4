package ru.dariamikhailukova.notebook_4.mvp.presenter.viewPager

import androidx.lifecycle.LiveData
import ru.dariamikhailukova.notebook_4.data.Note

interface ViewPagerPresenter {
    fun getAllData(): LiveData<List<Note>>
}