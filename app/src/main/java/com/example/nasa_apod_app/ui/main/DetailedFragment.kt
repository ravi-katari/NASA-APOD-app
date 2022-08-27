package com.example.nasa_apod_app.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.nasa_apod_app.MainActivity
import com.example.nasa_apod_app.R
import com.example.nasa_apod_app.model.GalleryInfo

private const val ARG_PARAM1 = "param1"

class DetailedFragment : Fragment() {

    private val TAG: String = DetailedFragment::class.java.toString()

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var galleryInfo: GalleryInfo

    private lateinit var ivGalleryPic: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvCopyright: TextView
    private lateinit var tvDescr: TextView
    private lateinit var ivFull: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            galleryInfo = it.getSerializable(ARG_PARAM1) as GalleryInfo
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(info: GalleryInfo) =
            DetailedFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, info)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWidgets(view)
        setupBackButton()
        initListeners()
        initCurrentData()
    }

    private fun initCurrentData() {

        context?.let {
            Glide
                .with(it)
                .load(galleryInfo.hdurl)
                .centerCrop()
                .thumbnail(Glide.with(it).load(R.drawable.loading_spinner))
                .into(ivGalleryPic)
        }

        // display title
        if(!galleryInfo.title.isNullOrEmpty()) {
            val title = galleryInfo.title + "(" + galleryInfo.service_version + ")"
            tvTitle.text = title
        } else {
            tvTitle.text = "NA"
        }

        // display copyright
        if(!galleryInfo.copyright.isNullOrEmpty()) {

            val copyright = resources.getString(R.string.copyright) + galleryInfo.copyright.replace("\n", "")

            tvCopyright.text = copyright
        }

        tvDescr.text = galleryInfo.explanation
    }

    private fun initWidgets(view: View) {
        ivGalleryPic = view.findViewById(R.id.iv_gal)
        ivFull = view.findViewById(R.id.iv_fullscr)
        tvTitle = view.findViewById(R.id.tv_title)
        tvCopyright = view.findViewById(R.id.tv_copyright)
        tvDescr = view.findViewById(R.id.tv_descr)
    }

    private fun setupBackButton() {
        (context as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initListeners() {
        ivFull.setOnClickListener {
            viewModel.navigateToFullScreenFragment(context, galleryInfo.hdurl)
        }
    }
}