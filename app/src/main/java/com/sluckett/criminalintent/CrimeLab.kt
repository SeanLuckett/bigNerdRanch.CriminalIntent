package com.sluckett.criminalintent

import java.util.*

object CrimeLab {
    private var mCrimes: MutableList<Crime> = ArrayList()

    init {
        for (i in 0..99) {
            val crime = Crime()
            crime.setTitle("Crime #$i")
            crime.setSolved(i % 2 == 0)
            mCrimes.add(crime)
        }
    }

    fun getCrimes(): List<Crime> { return mCrimes }

    fun getCrime(id: UUID): Crime? {
        for (crime in mCrimes) {
            if (crime.getId() == id) {
                return crime
            }
        }
        return null
    }
}