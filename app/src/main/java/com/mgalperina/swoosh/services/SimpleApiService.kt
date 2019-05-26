package com.mgalperina.swoosh.services

import com.mgalperina.swoosh.Model.User
import com.mgalperina.swoosh.api.JsonPlaceHolderService
import com.mgalperina.swoosh.api.JSON_PLACE_HOLDER_SERVICE_ENDPOINT
import rx.Observable
import retrofit.RestAdapter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SimpleApiService: ApiService  {

    //val restAdapters = kotlin.collections.MutableList(0, 0)

    fun <T> createRetrofitService(clazz: Class<T>, endPoint: String): T {
        val restAdapter = RestAdapter.Builder()
            .setEndpoint(endPoint)
            .build()

        return restAdapter.create(clazz)
    }

    override fun getUsers(): Observable<Array<User>> {
        val service = createRetrofitService(
            JsonPlaceHolderService::class.java,
            JSON_PLACE_HOLDER_SERVICE_ENDPOINT)

        return service.getUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())

    }
}