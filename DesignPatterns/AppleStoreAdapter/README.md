# Adapter

## Intent

Wrap an incompatible API so the rest of the app can depend on a stable Kotlin interface.

## Android/Kotlin Use Cases

- ViewModel and UI-state behavior that changes by product, account, checkout, or order state.
- Repository, service, and use-case boundaries that need testable contracts.
- Checkout, inventory, recommendation, analytics, and support flows where Apple Store examples map cleanly to Android app architecture.

## Kotlin Example

```kotlin
package com.mphocodes.androidpatterns.adapter

data class LegacyInventoryItem(val sku: String, val stockCount: Int)
class LegacyInventoryClient { fun fetchSku(sku: String) = LegacyInventoryItem(sku, stockCount = 7) }

data class InventoryStatus(val sku: String, val availableUnits: Int, val canShip: Boolean)
interface InventoryRepository { fun statusFor(sku: String): InventoryStatus }

class LegacyInventoryAdapter(private val client: LegacyInventoryClient) : InventoryRepository {
    override fun statusFor(sku: String): InventoryStatus {
        val item = client.fetchSku(sku)
        return InventoryStatus(item.sku, item.stockCount, item.stockCount > 0)
    }
}
```

## What To Notice

- The example uses Kotlin language features such as interfaces, data classes, objects, function interfaces, and expression bodies where they make the pattern clearer.
- The domain remains Apple Store-oriented, but the implementation is written as Android/Kotlin learning material.
- In a real Android app, keep these pattern roles behind package boundaries such as `domain`, `data`, and `presentation`.

## Practice Prompt

Adapt this pattern to a feature you know: a product details screen, cart flow, support journey, trade-in quote, or notification subscription.
