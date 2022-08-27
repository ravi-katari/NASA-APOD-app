package com.example.nasa_apod_app.ui.main

import android.app.Activity
import android.content.Context
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

    /**
     * Reading json data from assets(data.json)
     * */
    fun getGalleryInfo(context: Context?) {
        context?.let {
            galleryInfoLiveData.postValue(Datasource().loadGalleryData(it, "data.json"))
        }
    };

    fun navigateToDetailedFragment(context: Context?, selectedItemIndex: Int) {
        loadFragment(context, DetailedFragment.newInstance(selectedItemIndex))
    }
    fun loadFragment(context: Context?, fragment: Fragment) {
        (context as MainActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}