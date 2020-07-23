package com.example.stage2task5

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stage2task5.adapter.CatAdapter
import com.example.stage2task5.vm.CatViewModel
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainFragment : Fragment() {
    private val TAG = "MainAct"

    private val catViewModel by viewModels<CatViewModel>()
    private val itemAdapter = CatAdapter {
        findNavController().navigate(R.id.action_Open_Cat_Image, bundleOf("Cat" to it.imageUrl))
//        Toast.makeText(parentFragment?.context, "You click on the image ${it.id}", Toast.LENGTH_SHORT).show()
        Log.e(TAG, "click was here ${it.id}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(context)
        }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)){
                    catViewModel.getPageOfCats()
                    Log.e(TAG, "loading more")
                }
            }
        })

        catViewModel.items.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer
            itemAdapter.submitList(it)
            itemAdapter.notifyDataSetChanged()  //need to fix bug
            Log.e(TAG, "submit list to adapter")
        })
    }
}
