package com.example.greedygame.imageData

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChildrenData {

    @Expose
    @SerializedName("thumbnail")
    private val thumbnail: String? = null

    public fun getThumnail(): String? {
        return thumbnail
    }

    override fun toString(): String {
        return thumbnail.toString()
    }
}