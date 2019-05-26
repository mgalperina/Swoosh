package com.mgalperina.swoosh.services

import com.mgalperina.swoosh.Model.User
import rx.Observable

interface ApiService {
    fun getUsers(): Observable<Array<User>>
}