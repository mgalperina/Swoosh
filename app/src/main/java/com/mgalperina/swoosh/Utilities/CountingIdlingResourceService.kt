package com.mgalperina.swoosh.Utilities

class CountingIdlingResourceService {
    private val _counters = mutableMapOf<String, SimpleCountingIdlingResource>()

    fun get(key: String): SimpleCountingIdlingResource {
        return _counters.getOrPut(key, { SimpleCountingIdlingResource(key) })
    }

    fun increment(key: String) {
        get(key).increment()
    }

    fun decrement(key: String) {
        get(key).decrement()
    }
}

