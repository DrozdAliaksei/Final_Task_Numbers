package com.example.stage2task6.mvp.view.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import com.example.stage2task6.R
import com.example.stage2task6.data.local.model.Film
import kotlinx.android.synthetic.main.film_fragment.film_description
import kotlinx.android.synthetic.main.film_fragment.videoView

class FilmFragment : Fragment() {

    private var position = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.film_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val film: Film = arguments?.getParcelable("Film")!!
        videoView.setVideoURI(Uri.parse(film.mediaUrl?.first()))
        videoView.setMediaController(MediaController(requireContext()))
        videoView.requestFocus(0)
        film_description.text = film.description

        videoView.setOnPreparedListener {
            videoView.seekTo(position)
            if (position == 0) {
                videoView.start()
            } else {
                videoView.pause()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("Position", videoView.currentPosition)
        videoView.pause()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("Position")
        }
        videoView.seekTo(position)
    }
}
