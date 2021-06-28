package com.example.codelabadvanceandroidfragmentsowndemo

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NavUtils
import com.example.codelabadvanceandroidfragmentsowndemo.content.SongUtils
import com.example.codelabadvanceandroidfragmentsowndemo.content.SongUtils.Song


class SongDetailActivity : AppCompatActivity() {

    var mSong: Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)

        // Show the Up button in the action bar.
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
//
//        mSong = SongUtils.SONG_ITEMS[intent.getIntExtra(SongUtils.SONG_ID_KEY, 0)]
//
//        if (mSong != null) {
//            (findViewById<View>(R.id.song_detail) as TextView).text = mSong!!.details
//        }

        if (savedInstanceState == null) {
            val selectedSong = intent.getIntExtra(SongUtils.SONG_ID_KEY, 0)
            val fragment = SongDetailFragment().newInstance(selectedSong)
            supportFragmentManager.beginTransaction()
                .add(R.id.song_detail_container, fragment)
                .commit()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        if (id == R.id.home) {
            NavUtils.navigateUpTo(this, Intent(this, MainActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}