package com.example.weat.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weat.models.WeatModel
import com.example.weat.serv.WeatServ
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel: ViewModel() {

    private val SERV = WeatServ()
    private val disposable = CompositeDisposable()
    val weatherData = MutableLiveData<WeatModel>()

    fun getDataFromAPI(cityName: String) {
        disposable.add(
            SERV.getDataServ(cityName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({weatherData.value = it},{Log.d(TAG, "getWeather: $it")})
        )

    }
}