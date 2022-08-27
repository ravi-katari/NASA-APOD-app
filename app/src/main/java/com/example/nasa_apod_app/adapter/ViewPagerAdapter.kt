package com.example.nasa_apod_app.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nasa_apod_app.MainActivity
import com.example.nasa_apod_app.model.GalleryInfo
import com.example.nasa_apod_app.ui.main.DetailedFragment

class ViewPagerAdapter(
    context: Context,
    private val listGalleryInfo: List<GalleryInfo>
) :
    FragmentStateAdapter(context as MainActivity) {

    override fun getItemCount(): Int {
        return listGalleryInfo.size
    }

    override fun createFragment(position: Int): Fragment {
        return DetailedFragment.newInstance(listGalleryInfo[position])
    }
}