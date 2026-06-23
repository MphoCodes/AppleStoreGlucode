package com.mphocodes.androidpatterns.singleton

object StoreAnalytics {
    private val events = mutableListOf<String>()
    fun track(event: String) { events += event }
    fun recentEvents(): List<String> = events.toList()
}
