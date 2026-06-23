package com.mphocodes.androidpatterns.observer

data class StockUpdate(val sku: String, val available: Boolean)
fun interface StockObserver { fun onStockChanged(update: StockUpdate) }
class StockNotifier {
    private val observers = mutableSetOf<StockObserver>()
    fun subscribe(observer: StockObserver) { observers += observer }
    fun unsubscribe(observer: StockObserver) { observers -= observer }
    fun publish(update: StockUpdate) = observers.forEach { it.onStockChanged(update) }
}
