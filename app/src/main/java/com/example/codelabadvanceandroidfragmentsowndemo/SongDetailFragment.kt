package com.example.codelabadvanceandroidfragmentsowndemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.codelabadvanceandroidfragmentsowndemo.content.SongUtils
import kotlinx.coroutines.selects.select

class SongDetailFragment : Fragment() {

    var mSong: SongUtils.Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (requireArguments().containsKey(SongUtils.SONG_ID_KEY)) {
            mSong = SongUtils.SONG_ITEMS.get(requireArguments().getInt(SongUtils.SONG_ID_KEY))
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.song_detail, container, false)

        if (mSong != null) {
            rootView.findViewById<TextView>(R.id.song_detail).setText(mSong!!.details)
        }

        return rootView
    }

    fun newInstance(selectSong: Int): SongDetailFragment {
        val fragment = SongDetailFragment()
        val arguments = Bundle()
        arguments.putInt(SongUtils.SONG_ID_KEY, selectSong)
        fragment.arguments = arguments
        return fragment
    }


}