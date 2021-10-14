package com.example.weat.serv

import com.example.weat.models.WeatModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query



interface WeatAPI {

    @GET("data/2.5/weather?&units=metric&appid=ffab31f93b27f5216d5b88a6e908a6e7")
    fun getData(
        @Query("q") cityName: String): Single<WeatModel>
}