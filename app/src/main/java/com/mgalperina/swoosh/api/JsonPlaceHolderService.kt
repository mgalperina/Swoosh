package com.mgalperina.swoosh.api

import com.mgalperina.swoosh.Model.User
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

const val JSON_PLACE_HOLDER_SERVICE_ENDPOINT = "https://api.github.com"

interface JsonPlaceHolderService {

    @GET("/users/{login}")
    fun getUsers(): Observable<Array<User>>
}
