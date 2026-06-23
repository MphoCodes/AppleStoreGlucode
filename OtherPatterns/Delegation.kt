package com.mphocodes.androidpatterns.other

interface AnalyticsTracker { fun track(event: String) }
class ConsoleAnalyticsTracker : AnalyticsTracker { override fun track(event: String) = println(event) }
class ProductDetailsTracker(private val tracker: AnalyticsTracker) : AnalyticsTracker by tracker
