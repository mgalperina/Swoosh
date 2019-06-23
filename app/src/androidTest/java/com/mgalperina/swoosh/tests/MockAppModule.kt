package com.mgalperina.swoosh.tests

import com.mgalperina.swoosh.AppModule
import com.mgalperina.swoosh.Model.Address
import com.mgalperina.swoosh.Model.Company
import com.mgalperina.swoosh.Model.Geo
import com.mgalperina.swoosh.Model.User
import com.mgalperina.swoosh.services.ApiService
import com.mgalperina.swoosh.services.SimpleApiService
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.*
import javax.inject.Singleton
import org.mockito.Mockito.`when`
import io.reactivex.Observable.fromCallable
import org.fluttercode.datafactory.impl.DataFactory
import kotlin.random.Random


@Module
class MockAppModule: AppModule() {
    private val mFake = DataFactory()

    @Provides
    @Singleton
    override fun provideApiService(): ApiService {
        val service =
            mock(SimpleApiService::class.java)

        val mockUsers = getMockUsers()

        `when`(service.getUsers())
            .thenReturn(fromCallable { mockUsers })

        return service
    }

    fun getMockUsers(): List<User> {
        return MutableList(10) {
            User(
                Address(mFake.city,
                    Geo("1", "2"),
                    mFake.streetName,
                    mFake.streetSuffix,
                    "0" ),
                Company("","", mFake.name),
                mFake.emailAddress,
                1,
                mFake.name,
                "000-000-000",
                mFake.name,
                mFake.emailAddress)
        }

    }
}