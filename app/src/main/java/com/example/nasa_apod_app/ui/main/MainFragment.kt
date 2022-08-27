package com.example.nasa_apod_app.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.data.Datasource
import com.example.nasa_apod_app.MainActivity
import com.example.nasa_apod_app.R
import com.example.nasa_apod_app.adapter.ItemAdapter
import com.example.nasa_apod_app.model.GalleryInfo

class MainFragment : Fragment() {


    private val TAG: String = MainFragment::class.java.toString()

    companion object {
        @JvmStatic
        var TITLE: String = "Gallery"
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by activityViewModels()

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
        initViewModel()
        setPageTitle()
    }

    private fun setPageTitle() {
        viewModel.setScreenTitle(context, "Gallery")
    }

    private fun initViewModel() {

        viewModel.galleryInfoLiveData.observe(viewLifecycleOwner) { list ->
            Log.d(TAG, list.size.toString())
            setAdapter(list)
        }

        // During coming back from Detailed Info Screen should disable it
        viewModel.showOrHideBackButton(context, false)

        // Loading info from Assets here
        viewModel.getGalleryInfo(context)
    }

    private fun setAdapter(dataSetGalleryInfo: List<GalleryInfo>) {

        recyclerView.adapter = context?.let {
            ItemAdapter(it, dataSetGalleryInfo) { position ->
                viewModel.currentPositionLiveData.setValue(position)
                viewModel.navigateToViewPagerFragment(context)
            }
        }

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