package com.example.nasa_apod_app.ui.main

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.affirmations.data.Datasource
import com.example.nasa_apod_app.MainActivity
import com.example.nasa_apod_app.R
import com.example.nasa_apod_app.model.GalleryInfo

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
            galleryInfoLiveData.postValue(Datasource().loadGalleryData(it, "data.json"))
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