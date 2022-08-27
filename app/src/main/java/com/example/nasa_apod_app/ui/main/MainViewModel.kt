package com.example.nasa_apod_app.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.affirmations.data.Datasource
import com.example.nasa_apod_app.MainActivity
import com.example.nasa_apod_app.R
import com.example.nasa_apod_app.model.GalleryInfo
import com.google.android.material.snackbar.Snackbar

class MainViewModel : ViewModel() {

    val galleryInfoLiveData: MutableLiveData<List<GalleryInfo>> by lazy {
        MutableLiveData<List<GalleryInfo>>()
    }

    val currentPositionLiveData: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    /**
     * Reading json data from assets(data.json)
     * */
    fun getGalleryInfo(context: Context?) {

        context?.let {

            /**
             * Sort by Date Descending(To show latest First)
             * And
             * Filter for [media_type] is [image] only
             * */
            var listGalleryInfo: List<GalleryInfo> = Datasource().loadGalleryData(it, "data.json")

            // Sorting
            listGalleryInfo.sortedByDescending { it.date }
            // Filtering
            listGalleryInfo = listGalleryInfo.filter { it.media_type == "image" }

            galleryInfoLiveData.postValue(listGalleryInfo)
        }
    };

    /**
     * Below function has been used to Show/Hide
     * HomeUp Button(Back button) in ActionBar
     * */
    fun showOrHideBackButton(context: Context?, shouldEnable: Boolean) {
        (context as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(shouldEnable)
    }

    fun setScreenTitle(context: Context?, title: String) {
        (context as MainActivity).setTitle(title)
    }

    fun navigateToViewPagerFragment(context: Context?) {
        loadFragment(context, ViewPagerFragment())
        Snackbar.make(
            (context as MainActivity).findViewById(R.id.container),
            "Swipe Left/Right to see Prev/Next Item",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    fun navigateToFullScreenFragment(context: Context?, imageUrl: String) {
        loadFragment(context, ImageFullscreenFragment.newInstance(imageUrl))
    }

    fun loadFragment(context: Context?, fragment: Fragment) {
        (context as MainActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }
}