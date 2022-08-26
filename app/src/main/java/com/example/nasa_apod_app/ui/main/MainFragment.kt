package com.example.nasa_apod_app.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.data.Datasource
import com.example.nasa_apod_app.R
import com.example.nasa_apod_app.adapter.ItemAdapter
import com.example.nasa_apod_app.model.GalleryInfo

class MainFragment : Fragment() {


    private val TAG: String = MainFragment::class.java.toString()

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(view)
    }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun setAdapter(dataSetGalleryInfo: List<GalleryInfo>) {

        recyclerView.adapter = context?.let { ItemAdapter(it, dataSetGalleryInfo) {
            position ->  viewModel.navigateToDetailedFragment(context, position)
        }}

        /**
         * Using below to improve performance
         * since changes in the content
         * do not change the layout size
         */
        recyclerView.setHasFixedSize(true)
    }

    private fun initRecyclerView(view: View) {
         recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
    }
}