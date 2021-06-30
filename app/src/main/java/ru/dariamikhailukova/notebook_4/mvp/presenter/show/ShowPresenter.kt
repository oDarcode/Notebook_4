package ru.dariamikhailukova.notebook_4.mvp.presenter.show

import ru.dariamikhailukova.notebook_4.data.Note
import java.util.*

interface ShowPresenter {
    fun sendEmail(name: String, text: String, date: String)
    fun update(name: String, text: String)
    fun delete(currentNote: Note)
    fun inputCheck(name: String, text: String): Boolean
}