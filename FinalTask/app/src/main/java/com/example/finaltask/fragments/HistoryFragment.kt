package com.example.finaltask.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finaltask.MainActivity
import com.example.finaltask.R
import com.example.finaltask.adapter.NumberAdapter
import com.example.finaltask.data.local.model.Number
import com.example.finaltask.vm.HistoryViewModel
import kotlinx.android.synthetic.main.history_fragment.history_bnm
import kotlinx.android.synthetic.main.history_fragment.history_list
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {
    private val historyViewModel: HistoryViewModel by viewModel()
    private val numberAdapter = NumberAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.title = "History"

        history_list.apply {
            adapter = numberAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        historyViewModel.list.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer
            if (it.isEmpty()) {
                numberAdapter.setList(listOf(Number(text = "History is empty")))
            } else {
                numberAdapter.setList(it)
            }
        })

        history_bnm.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.clear_history -> {
                    historyViewModel.cleanHistory()
                    true
                }
                else -> false
            }
        }
    }
}
