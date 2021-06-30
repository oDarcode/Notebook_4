package ru.dariamikhailukova.notebook_4.mvp.presenter.add

import java.util.*

interface AddPresenter {
    fun insertDataToDatabase(name: String, text: String)
    fun inputCheck(name: String, text: String): Boolean
    //fun getDate(): Date
}