package com.mgalperina.swoosh.services

import com.mgalperina.swoosh.Model.User
import io.reactivex.Observable

interface ApiService {
    fun getUsers(): Observable<List<User>>
}