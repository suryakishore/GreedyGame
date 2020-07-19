package com.example.greedygame.imageData

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ImageResponse {

    @Expose
    @SerializedName("data")
    private val data: ImageData? = null
    public fun getImageData(): ImageData? {
        return data

    }


}