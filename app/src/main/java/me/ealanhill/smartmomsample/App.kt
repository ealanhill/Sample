package me.ealanhill.smartmomsample

import android.app.Application
import me.ealanhill.smartmomsample.networking.Api
import me.ealanhill.smartmomsample.networking.ApiModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {

    companion object {
        lateinit var COMPONENT: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.smartmom.co")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        COMPONENT = DaggerAppComponent.builder()
                .apiModule(ApiModule(retrofit.create(Api::class.java)))
                .contextModule(ContextModule(this))
                .build()
    }
}