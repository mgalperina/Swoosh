package com.mgalperina.swoosh.tests

import com.mgalperina.swoosh.AppComponent
import com.mgalperina.swoosh.DaggerAppComponent
import com.mgalperina.swoosh.SwooshApplication

class SwooshApplication4Tests: SwooshApplication() {

    override fun createMyComponent(): AppComponent {
        return DaggerAppComponent
            .builder()
            .appModule(MockAppModule())
            .build()
    }
}