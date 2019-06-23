package com.mgalperina.swoosh.Utilities

object CountingIdlingResourceService {
    private val mCounters =
        mutableMapOf<String, SimpleCountingIdlingResource>()

    @JvmStatic
    fun get(key: String): SimpleCountingIdlingResource {
        return mCounters.getOrPut(key, { SimpleCountingIdlingResource(key) })
    }

    fun increment(key: String) {
        get(key).increment()
    }

    fun decrement(key: String) {
        val resource = get(key)

        if(resource.isIdleNow()) {
            resource.decrement()
        }
    }
}

