package me.ealanhill.smartmomsample

import android.app.Application
import me.ealanhill.smartmomsample.networking.Api
import me.ealanhill.smartmomsample.networking.ApiModule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {

    companion object {
        lateinit var COMPONENT: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.smartmom.co")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        COMPONENT = DaggerAppComponent.builder()
                .apiModule(ApiModule(retrofit.create(Api::class.java)))
                .contextModule(ContextModule(this))
                .build()
    }
}