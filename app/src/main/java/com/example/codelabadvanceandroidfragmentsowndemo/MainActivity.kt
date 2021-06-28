package com.example.codelabadvanceandroidfragmentsowndemo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {

    private lateinit var openButton: Button
    private var isFragmentDisplayed: Boolean = false
    val STATE_FRAGMENT = "state_of_fragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openButton = findViewById(R.id.open_button)

        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT)
            if (isFragmentDisplayed) {
                openButton.setText(R.string.close)
            }
        }

        openButton.setOnClickListener {
            if (!isFragmentDisplayed) {
                displayFragment()
            } else {
                closeFragment()
            }
        }

//            savedInstanceState?.let {
//                isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT)
//                if (isFragmentDisplayed) displayFragment()
//                else closeFragment()
//            }

    }

    private fun displayFragment() {
        val simpleFragment = SimpleFragment().newInstance()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.fragment_container, simpleFragment)
            .addToBackStack(null).commit()
        openButton.text = getString(R.string.close)
        isFragmentDisplayed = true
    }

    private fun closeFragment() {
        val fragmentManager = supportFragmentManager
        val simpleFragment: SimpleFragment? =
            fragmentManager.findFragmentById(R.id.fragment_container) as SimpleFragment?

        if (simpleFragment != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(simpleFragment).commit()
        }

//        simpleFragment?.let {
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.remove(simpleFragment).commit()
//        }
        openButton.text = getString(R.string.open)
        isFragmentDisplayed = false
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed)
        super.onSaveInstanceState(savedInstanceState)
    }

}