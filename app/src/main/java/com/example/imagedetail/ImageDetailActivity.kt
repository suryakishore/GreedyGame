package com.example.imagedetail

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.greedygame.Constants.IMAGE_DATA
import com.example.greedygame.R
import com.example.greedygame.databinding.ActivityImageDetailBinding

/**
 * This activity is used to show the image in detail
 */
class ImageDetailActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityImageDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeData()
    }

    /**
     * initialize data
     */
    private fun initializeData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_detail)
        val imageUrl = intent.getStringExtra(IMAGE_DATA)
        if (!TextUtils.isEmpty(imageUrl)) {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.logo)
            requestOptions.error(R.drawable.logo)
            Glide.with(this).load(imageUrl)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mBinding.ivDetail)
        }
        mBinding.ivBack.setOnClickListener {
            finish()
        }
    }

}