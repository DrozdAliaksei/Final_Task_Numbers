package com.example.finaltask.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.finaltask.MainActivity
import com.example.finaltask.R
import com.example.finaltask.util.Constants.RANDOM_DATE
import com.example.finaltask.util.Constants.RANDOM_MATH
import com.example.finaltask.util.Constants.RANDOM_NUMBER
import com.example.finaltask.util.Constants.RANDOM_YEAR
import kotlinx.android.synthetic.main.main_fragment.history
import kotlinx.android.synthetic.main.main_fragment.random_date_info
import kotlinx.android.synthetic.main.main_fragment.random_math_number_info
import kotlinx.android.synthetic.main.main_fragment.random_number_info
import kotlinx.android.synthetic.main.main_fragment.random_year_info

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = "Final task"
        random_number_info.setOnClickListener {
            findNavController().navigate(
                R.id.DetailFragment,
                bundleOf("Api" to RANDOM_NUMBER)
            )
        }
        random_math_number_info.setOnClickListener {
            findNavController().navigate(
                R.id.DetailFragment,
                bundleOf("Api" to RANDOM_MATH)
            )
        }
        random_date_info.setOnClickListener {
            findNavController().navigate(
                R.id.DetailFragment,
                bundleOf("Api" to RANDOM_DATE)
            )
        }
        random_year_info.setOnClickListener {
            findNavController().navigate(
                R.id.DetailFragment,
                bundleOf("Api" to RANDOM_YEAR)
            )
        }
        history.setOnClickListener {
            findNavController().navigate(R.id.HistoryFragment)
        }
    }
}
