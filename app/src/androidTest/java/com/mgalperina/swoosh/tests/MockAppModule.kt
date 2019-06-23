package com.mgalperina.swoosh.tests

import com.mgalperina.swoosh.AppModule
import com.mgalperina.swoosh.Model.User
import com.mgalperina.swoosh.services.ApiService
import com.mgalperina.swoosh.services.SimpleApiService
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import org.mockito.Mockito.*
import javax.inject.Singleton
import org.mockito.Mockito.`when`
import io.reactivex.Observable.fromCallable


@Module
class MockAppModule: AppModule() {
    @Provides
    @Singleton
    override fun provideApiService(): ApiService {
        val service =
            mock(SimpleApiService::class.java)

        `when`(service.getUsers())
            .thenReturn(getUsersResponse())

        return service
    }

    fun getUsersResponse(): Observable<List<User>> {
        val mockList =
            MutableList(10) {
                mock(User::class.java)
            }

        return fromCallable { mockList }
    }
}