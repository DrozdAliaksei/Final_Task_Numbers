package com.example.stage2task5

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stage2task5.adapter.CatAdapter
import com.example.stage2task5.vm.CatViewModel
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity() {

    private val TAG ="MainAct"

    private val catViewModel by viewModels<CatViewModel>()
    private val itemAdapter = CatAdapter{
//        val intent = Intent(this,ImageActivity::class.java)
//        startActivityForResult(intent)
        Toast.makeText(this,"You click on the image ${it.id}",Toast.LENGTH_SHORT).show()
        Log.e(TAG,"click was here ${it.id}")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    catViewModel.getPageOfCats()
                    Log.e(TAG,"loading more")
                }
            }
        })

        catViewModel.items.observe(this, Observer {
            it ?: return@Observer
            itemAdapter.submitList(it)
//            itemAdapter.notifyDataSetChanged()
            Log.e(TAG,"submit list to adapter")
        })
    }

}
