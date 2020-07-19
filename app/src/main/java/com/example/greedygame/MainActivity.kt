package com.example.greedygame

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.greedygame.Constants.IMAGE_DATA
import com.example.greedygame.databinding.ActivityMainBinding
import com.example.greedygame.imageData.ChildrensImageData
import com.example.imagedetail.ImageDetailActivity

/**
 * This activity is used to show the images
 */
class MainActivity : AppCompatActivity(), SelectItem {

    lateinit var mBinding: ActivityMainBinding
    lateinit var mImagesViewModel: ImagesViewModel
    private var mImageData = ArrayList<ChildrensImageData>()
    lateinit var adapter: ImagesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeData()
        subscribeImageData()
    }

    /**
     * subscribe to image data which is coming from server
     */
    private fun subscribeImageData() {
        mImagesViewModel.onImageData().observe(this, Observer {
            mBinding.pbImages.visibility = View.GONE
            if (it != null) {
                mImageData.clear()
                mImageData.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

    /**
     * initialize data
     */
    private fun initializeData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mImagesViewModel = ViewModelProviders.of(this).get(ImagesViewModel::class.java)
        mBinding.viewModel = mImagesViewModel
        adapter = ImagesAdapter(mImageData, this)
        mBinding.rvSelectPartner.adapter = adapter
        mBinding.pbImages.visibility = View.VISIBLE
        mImagesViewModel.getImages(this)
    }

    override fun onSelectItem(pos: Int) {
        val intent = Intent(this, ImageDetailActivity::class.java)
        intent.putExtra(IMAGE_DATA, mImageData.get(pos).getChildrenData()!!.getThumnail())
        startActivity(intent)
    }
}