package com.example.greedygame.imageData

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChildrensImageData {

    @Expose
    @SerializedName("data")
    private val data: ChildrenData?=null

    public fun getChildrenData(): ChildrenData? {
        return data
    }


}