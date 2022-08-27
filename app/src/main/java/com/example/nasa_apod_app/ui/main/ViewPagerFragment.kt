package com.example.nasa_apod_app.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.nasa_apod_app.MainActivity
import com.example.nasa_apod_app.R
import com.example.nasa_apod_app.adapter.ViewPagerAdapter
import com.example.nasa_apod_app.model.GalleryInfo


class ViewPagerFragment : Fragment() {

    private val TAG: String = ViewPagerFragment::class.java.toString()

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var dataSetGalleryInfo: List<GalleryInfo>

    private lateinit var viewPager: ViewPager2

    companion object {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initViewPager(view)
        setPageTitle()
        setupBackButton()
    }

    private fun setPageTitle() {
        viewModel.setScreenTitle(context, "Detailed Info")
    }

    private fun setupBackButton() {
        viewModel.showOrHideBackButton(context, true)
    }

    private fun initViewPager(view: View) {
        viewPager = view.findViewById(R.id.viewpager)
    }

    private fun setAdapterForViewPager(selectedIndex: Int) {
        context?.let {
            viewPager.adapter = ViewPagerAdapter(it, dataSetGalleryInfo)
            viewPager.setCurrentItem(selectedIndex, false)
        }
    }

    private fun initViewModel() {

        context?.let {

            viewModel.galleryInfoLiveData.observe(viewLifecycleOwner) { list ->
                dataSetGalleryInfo = list
            };

            viewModel.currentPositionLiveData.observe(viewLifecycleOwner) {
                setAdapterForViewPager(it)
            }
        }
    }
}