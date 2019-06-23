package com.mgalperina.swoosh

import com.mgalperina.swoosh.Controller.FinishActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject (activity: FinishActivity)
}