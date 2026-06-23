package com.mphocodes.androidpatterns.memento

data class CartSnapshot(val items: List<String>)
class Cart {
    private val items = mutableListOf<String>()
    fun add(sku: String) { items += sku }
    fun save(): CartSnapshot = CartSnapshot(items.toList())
    fun restore(snapshot: CartSnapshot) { items.clear(); items += snapshot.items }
    fun currentItems(): List<String> = items.toList()
}
