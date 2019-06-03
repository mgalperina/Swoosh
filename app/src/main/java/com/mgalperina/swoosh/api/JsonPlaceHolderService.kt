package com.mgalperina.swoosh.api

import com.mgalperina.swoosh.Model.User
import retrofit2.http.GET
import io.reactivex.Observable

const val JSON_PLACE_HOLDER_SERVICE_ENDPOINT = "https://jsonplaceholder.typicode.com"

interface JsonPlaceHolderService {

    @GET("/users")
    fun getUsers(): Observable<List<User>>
}
