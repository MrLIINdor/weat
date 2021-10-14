package com.example.weat.main


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.weat.databinding.ActivityMainBinding
import com.example.weat.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var banding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        banding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(banding.root)


        banding.butWeater.setOnClickListener{
        var cityName = banding.edCity.getText().toString()
            viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
            viewModel.getDataFromAPI(cityName)
            getLive()
        }


        banding.butBack.setOnClickListener(){
            finish()
        }
    }


    private fun getLive() {
        viewModel.weatherData.observe(this, Observer { data->
            data?.let {
                banding.temp.text = data.main.temp.toString() + "°C"
                banding.country.text = data.sys.country
                banding.city.text = data.name
                banding.humidity.text = data.main.humidity.toString() + "%"
                banding.speed.text = data.wind.speed.toString() + " метр/сек"
                banding.deg.text = data.wind.deg.toString()
                banding.timezon.text = data.timezone.toString()

                Glide.with(this)
                    .load("https://openweathermap.org/img/wn/" + data.weather.get(0).icon + "@2x.png")
                    .into(banding.imgIcon)
            }
        })
    }

}