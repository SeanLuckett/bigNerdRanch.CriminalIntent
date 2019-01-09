package com.sluckett.criminalintent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.text.DateFormat

class CrimeListFragment : Fragment() {
    private lateinit var mCrimeRecyclerView: RecyclerView
    private lateinit var mAdapter: CrimeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)

        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view)
        mCrimeRecyclerView.layoutManager = LinearLayoutManager(activity)

        updateUI()

        return view
    }

    private fun updateUI() {
        val crimes: List<Crime> = CrimeLab.getCrimes()
        mAdapter = CrimeAdapter(crimes)
        mCrimeRecyclerView.adapter = mAdapter
    }


    // -------------- MEMBER CLASSES ----------------------------------------------------

    private class CrimeHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_crime, parent, false)),
        View.OnClickListener {

        private val mTitleTextView: TextView = itemView.findViewById(R.id.crime_title)
        private val mDateTextView: TextView = itemView.findViewById(R.id.crime_date)
        private val mSolvedImageView: ImageView = itemView.findViewById(R.id.crime_solved)

        private lateinit var mCrime: Crime

        fun bind(crime: Crime) {
            mCrime = crime
            mTitleTextView.text = mCrime.getTitle()

            val dateFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM)
            mDateTextView.text = dateFormatter.format(mCrime.getDate())

            mSolvedImageView.setVisibility(if (crime.isSolved()) View.VISIBLE else View.GONE)

            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            Toast.makeText(view.context, "${mCrime.getTitle()} clicked!", Toast.LENGTH_SHORT).show()
        }
    }

    private class CrimeAdapter(crimes: List<Crime>) : RecyclerView.Adapter<CrimeHolder>() {
        private var mCrimes = crimes

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return CrimeHolder(layoutInflater, parent)
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = mCrimes.get(position)
            holder.bind(crime)
        }

        override fun getItemCount(): Int {
            return mCrimes.size
        }
    }
}