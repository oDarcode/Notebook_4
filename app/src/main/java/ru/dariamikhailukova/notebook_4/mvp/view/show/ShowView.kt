package ru.dariamikhailukova.notebook_4.mvp.view.show

import java.util.*

interface ShowView {
    fun initView()
    fun updateItem()
    fun deleteNote()
    fun showToast(text: Int)
    fun sendIntent(name: String, text: String)
    fun returnToList()
    fun currentNoteId(): Long
    fun getDate(currentDate: Date): String
}