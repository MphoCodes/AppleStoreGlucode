package com.mphocodes.androidpatterns.proxy

data class ProductDetails(val sku: String, val description: String)
interface ProductDetailsRepository { fun detailsFor(sku: String): ProductDetails }
class NetworkProductDetailsRepository : ProductDetailsRepository {
    override fun detailsFor(sku: String) = ProductDetails(sku, "Fetched details for $sku")
}
class CachedProductDetailsProxy(private val delegate: ProductDetailsRepository) : ProductDetailsRepository {
    private val cache = mutableMapOf<String, ProductDetails>()
    override fun detailsFor(sku: String): ProductDetails = cache.getOrPut(sku) { delegate.detailsFor(sku) }
}
