package ru.dariamikhailukova.notebook_4.mvp.presenter.list

import androidx.lifecycle.LiveData
import ru.dariamikhailukova.notebook_4.data.Note

interface ListPresenter {
    fun deleteAll()
    fun getAllData(): LiveData<List<Note>>
}