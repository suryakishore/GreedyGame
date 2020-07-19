package com.example.greedygame;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;

public interface NetworkService {

    @GET("r/images/hot.json")
    Observable<Response<ResponseBody>> getImages();

}
