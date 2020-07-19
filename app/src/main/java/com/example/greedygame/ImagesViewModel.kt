package com.example.greedygame

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.greedygame.Constants.SUCCESS
import com.example.greedygame.imageData.ChildrensImageData
import com.example.greedygame.imageData.ImageResponse
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*

/**
 * view model class for the main activity.
 */
class ImagesViewModel() : ViewModel() {

    private var mImageData = MutableLiveData<ArrayList<ChildrensImageData>>()


    /**
     * This method used to get the images.
     */
    public fun getImages(context: Context) {
        val networkService = NetworkManager.initializeBaseUrlRetrofit(context)
        networkService.also { it ->
            it.getImages().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    if (it != null) {
                        try {
                            val jsonObject: JSONObject
                            val code = it.code()
                            if (code == SUCCESS) {
                                val response: String = it.body()!!.string()
                                Log.d("exe", "response : $response")
                                jsonObject = JSONObject(response)
                                val gson = Gson()
                                val imageResponse =
                                    gson.fromJson(jsonObject.toString(), ImageResponse::class.java)
                                if (imageResponse != null) {
                                    mImageData.postValue(
                                        imageResponse.getImageData()!!.getChildrenData()
                                    )
                                }
                            } else {
                                jsonObject = JSONObject(it.errorBody()!!.string())
                                mImageData.postValue(null)
                            }
                        } catch (e: JSONException) {
                            Log.d("exe", "JSONException : ${e.message}")
                            mImageData.postValue(null)
                        } catch (e: IOException) {
                            mImageData.postValue(null)
                            Log.d("exe", "IOException : ${e.message}")
                        }
                    }
                }
        }
    }


    /**
     * notify activity partner image comes
     */
    fun onImageData(): MutableLiveData<ArrayList<ChildrensImageData>> {
        return mImageData
    }


}