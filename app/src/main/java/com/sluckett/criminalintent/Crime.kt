package com.sluckett.criminalintent

import java.util.*

class Crime(id: UUID = UUID.randomUUID(), date: Date = Date()) {
    private val mId = id
    private var mDate = date
    private var mTitle : String? = null
    private var mSolved : Boolean = false

    fun getId() : UUID { return mId }

    fun getTitle() : String { return "$mTitle" }
    fun setTitle(title : String) { mTitle = title }

    fun getDate() : Date { return mDate }
    fun setDate(date: Date) { mDate = date }

    fun isSolved() : Boolean { return mSolved }
    fun setSolved(solved : Boolean) { mSolved = solved }
}