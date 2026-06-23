# Proxy

## Intent

Control access to another object, often to add caching, security, or lazy loading.

## Android/Kotlin Use Cases

- ViewModel and UI-state behavior that changes by product, account, checkout, or order state.
- Repository, service, and use-case boundaries that need testable contracts.
- Checkout, inventory, recommendation, analytics, and support flows where Apple Store examples map cleanly to Android app architecture.

## Kotlin Example

```kotlin
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
```

## What To Notice

- The example uses Kotlin language features such as interfaces, data classes, objects, function interfaces, and expression bodies where they make the pattern clearer.
- The domain remains Apple Store-oriented, but the implementation is written as Android/Kotlin learning material.
- In a real Android app, keep these pattern roles behind package boundaries such as `domain`, `data`, and `presentation`.

## Practice Prompt

Adapt this pattern to a feature you know: a product details screen, cart flow, support journey, trade-in quote, or notification subscription.
