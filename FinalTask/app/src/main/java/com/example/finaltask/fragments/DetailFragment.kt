package com.example.finaltask.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.finaltask.MainActivity
import com.example.finaltask.R
import com.example.finaltask.util.Constants.DETAIL_NUMBER
import com.example.finaltask.util.Constants.DETAIL_TEXT
import com.example.finaltask.util.Constants.RANDOM_DATE
import com.example.finaltask.util.Constants.RANDOM_MATH
import com.example.finaltask.util.Constants.RANDOM_NUMBER
import com.example.finaltask.util.Constants.RANDOM_YEAR
import com.example.finaltask.vm.DetailViewModel
import kotlinx.android.synthetic.main.detail_fragment.detail_title
import kotlinx.android.synthetic.main.detail_fragment.number
import kotlinx.android.synthetic.main.detail_fragment.refresh
import kotlinx.android.synthetic.main.detail_fragment.text
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("DETAIL", "onCreateView")
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("DETAIL", "onViewCreated start")
        val api = arguments?.getString("Api")
        when (api) {
            RANDOM_NUMBER -> setTitles(getString(R.string.random_number))
            RANDOM_MATH -> setTitles(getString(R.string.random_math))
            RANDOM_DATE -> setTitles(getString(R.string.random_date))
            RANDOM_YEAR -> setTitles(getString(R.string.random_year))
        }

        if (savedInstanceState == null) {
            api?.let { detailViewModel.getTypedNumber(it) }
            detailViewModel.number.observe(viewLifecycleOwner, Observer {
                it ?: return@Observer

                Log.i("DETAIL", "init fragment texts")
                number.text = it.name()
                text.text = it.text
            })
        }

        refresh.setOnClickListener { api?.let { it1 -> detailViewModel.getTypedNumber(it1) } }
    }

    private fun setTitles(title: String) {
        detail_title.text = title
        (activity as MainActivity).supportActionBar?.title = title
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Log.i("DETAIL", "onSaveInstanceState")
        outState.putString(DETAIL_NUMBER, number.text.toString())
        outState.putString(DETAIL_TEXT, text.text.toString())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {

            Log.i("DETAIL", "onViewStateRestored")
            number.text = savedInstanceState.getString(DETAIL_NUMBER)
            text.text = savedInstanceState.getString(DETAIL_TEXT)
            savedInstanceState.clear()
        }
    }
}
