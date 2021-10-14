package com.example.weat.serv

import com.example.weat.models.WeatModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatServ {


    private val URL = "http://api.openweathermap.org/"
    private val API = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(WeatAPI::class.java)


    fun getDataServ(cityName: String) : Single<WeatModel>{
        return API.getData(cityName)
    }

}