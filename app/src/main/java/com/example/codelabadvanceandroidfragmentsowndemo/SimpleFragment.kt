package com.example.codelabadvanceandroidfragmentsowndemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SimpleFragment : Fragment() {

    private val YES = 0
    private val NO = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_simple, container, false)
        val radioGroup: RadioGroup = rootView.findViewById(R.id.radio_group)
//        val ratingBar: RatingBar = rootView.findViewById(R.id.ratingBar)


        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = radioGroup.findViewById<RadioButton>(checkedId)
            val fragmentHeader: TextView = rootView.findViewById(R.id.fragment_header)
            val index: Int = radioGroup.indexOfChild(radioButton)

            when (index) {
                YES -> {
                    fragmentHeader.text = resources.getString(R.string.yes_message)
                }
                NO -> {
                    fragmentHeader.text = resources.getString(R.string.no_message)
                }
            }

        }

//        ratingBar.setOnRatingBarChangeListener { _, _, _ ->
//            val ratingString = resources.getString(R.string.my_rating) + "${ratingBar.rating}"
//
//            Toast.makeText(context, ratingString, Toast.LENGTH_SHORT).show()
//        }

        return rootView

    }

    fun newInstance() : SimpleFragment {
        return SimpleFragment()
    }
}