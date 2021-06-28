package com.example.codelabadvanceandroidfragmentsowndemo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.lang.ClassCastException


class SimpleFragment : Fragment() {

    private val YES = 0
    private val NO = 1
    private val NONE = 2
    private var mRadioButtonChoice: Int? = NONE
    private val CHOICE: String = "choice"
    var mListener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_simple, container, false)
        val radioGroup: RadioGroup = rootView.findViewById(R.id.radio_group)
//        val ratingBar: RatingBar = rootView.findViewById(R.id.ratingBar)

        if (arguments?.containsKey(CHOICE)!!) {
            mRadioButtonChoice = arguments?.getInt(CHOICE)
            if (mRadioButtonChoice != NONE) {
                radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice!!).id)
            }

        }


        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = radioGroup.findViewById<RadioButton>(checkedId)
            val fragmentHeader: TextView = rootView.findViewById(R.id.fragment_header)
            val index: Int = radioGroup.indexOfChild(radioButton)

            when (index) {
                YES -> {
                    fragmentHeader.text = resources.getString(R.string.yes_message)
                    mRadioButtonChoice = YES
                    mListener!!.onRadioButtonChoice(mRadioButtonChoice!!)
                }
                NO -> {
                    fragmentHeader.text = resources.getString(R.string.no_message)
                    mRadioButtonChoice = NO
                    mListener!!.onRadioButtonChoice(mRadioButtonChoice!!)
                }
                else -> {
                    mRadioButtonChoice = NONE
                    mListener!!.onRadioButtonChoice(NONE)
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

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw ClassCastException(context.toString() + resources.getString(R.string.exception_message))
        }

    }

    fun newInstance(choice: Int): SimpleFragment {
        val fragment = SimpleFragment()
        val arguments = Bundle()
        arguments.putInt(CHOICE, choice)
        fragment.arguments = arguments
        return fragment
    }
}

interface OnFragmentInteractionListener {
    fun onRadioButtonChoice(choice: Int)
}