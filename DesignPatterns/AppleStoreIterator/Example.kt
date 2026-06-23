package com.mphocodes.androidpatterns.iterator

data class StoreProduct(val sku: String, val name: String)
class ProductCatalog(private val products: List<StoreProduct>) : Iterable<StoreProduct> {
    override fun iterator(): Iterator<StoreProduct> = products.iterator()
    fun premiumProducts(): Sequence<StoreProduct> = products.asSequence().filter { it.name.contains("Pro") }
}
