package com.example.codelabadvanceandroidfragmentsowndemo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codelabadvanceandroidfragmentsowndemo.content.SongUtils

private var mTwoPane: Boolean = false

class SongMainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_main)

        val recyclerView: RecyclerView = findViewById(R.id.song_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(SongUtils.SONG_ITEMS)

        if (findViewById<FrameLayout>(R.id.song_detail_container) != null) {
            mTwoPane = true
        }

    }

    //adapter
    open class SimpleItemRecyclerViewAdapter(
        private val values: MutableList<SongUtils.Song>
    ) : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.SimpleItemViewHolder>() {

        class SimpleItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val songId = itemView.findViewById<TextView>(R.id.id)
            private val content = itemView.findViewById<TextView>(R.id.content)

            fun bind(song: SongUtils.Song) {
                content.text = song.song_title
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleItemViewHolder {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.song_list_content, parent, false)
            return SimpleItemViewHolder(view)
        }

        override fun onBindViewHolder(holder: SimpleItemViewHolder, position: Int) {
            holder.songId.text = "${(position+1)}"
            holder.bind(values[position])
            holder.itemView.setOnClickListener { view ->
                if (mTwoPane) {
                    val selectedSong = holder.adapterPosition
                    val fragment = SongDetailFragment().newInstance(selectedSong)

                    ((view.context) as AppCompatActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.song_detail_container, fragment)
                        .addToBackStack(null)
                        .commit()
                } else {
                    val intent = Intent(view.context, SongDetailActivity::class.java)
                    intent.putExtra(SongUtils.SONG_ID_KEY, holder.adapterPosition)
                    view.context.startActivity(intent)
                }
            }
        }

        override fun getItemCount(): Int {
            return values.size
        }
    }

}

