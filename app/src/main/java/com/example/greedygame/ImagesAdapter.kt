package com.example.greedygame

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.greedygame.databinding.ItemImageBinding
import com.example.greedygame.imageData.ChildrensImageData


/**
 * adapter class for the image items.
 */
public class ImagesAdapter(imageData: ArrayList<ChildrensImageData>, selectItem: SelectItem) :
    RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder>() {
    var mSelectItem: SelectItem
    var mImageData: ArrayList<ChildrensImageData>
    lateinit var mContext: Context

    init {
        mImageData = imageData
        mSelectItem = selectItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImagesAdapter.ImagesViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemImageBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_image, parent, false)
        return ImagesViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return if (mImageData != null) mImageData.size else 0
    }

    override fun onBindViewHolder(holder: ImagesAdapter.ImagesViewHolder, position: Int) {
        val imageUrl = mImageData.get(position).getChildrenData()!!.getThumnail()
        Log.d("exe", "imageUrl" + imageUrl)
        if (!TextUtils.isEmpty(imageUrl)) {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.logo)
            requestOptions.error(R.drawable.logo)
            Glide.with(mContext).load(imageUrl)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mItemBinding.ivThumbnail)
        }
        holder.mItemBinding.cvImage.setOnClickListener(View.OnClickListener {
            mSelectItem.onSelectItem(position)
        })
    }

    /**
     * view holder class for the image items
     */
    class ImagesViewHolder(itemBinding: ItemImageBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var mItemBinding: ItemImageBinding

        init {
            mItemBinding = itemBinding
        }
    }
}