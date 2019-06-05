package com.mgalperina.swoosh.services

import com.mgalperina.swoosh.Model.User
import com.mgalperina.swoosh.api.JsonPlaceHolderService
import com.mgalperina.swoosh.api.JSON_PLACE_HOLDER_SERVICE_ENDPOINT
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.schedulers.Schedulers

class SimpleApiService: ApiService  {
    private val baseUrl = JSON_PLACE_HOLDER_SERVICE_ENDPOINT
    private val clazz = JsonPlaceHolderService::class.java

    fun buildRetrofitFactory(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    override fun getUsers(): Observable<List<User>> {
        val service = buildRetrofitFactory().create(clazz)

        return service.getUsers()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
    }
}