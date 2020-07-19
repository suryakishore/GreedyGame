package com.example.greedygame.imageData

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ImageData {
    @Expose
    @SerializedName("children")
    private val children: ArrayList<ChildrensImageData>? = null

    public fun getChildrenData(): ArrayList<ChildrensImageData>? {
        return children;
    }
}