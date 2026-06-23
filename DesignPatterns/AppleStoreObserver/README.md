# Observer

## Intent

Notify interested objects when state changes.

## Android/Kotlin Use Cases

- ViewModel and UI-state behavior that changes by product, account, checkout, or order state.
- Repository, service, and use-case boundaries that need testable contracts.
- Checkout, inventory, recommendation, analytics, and support flows where Apple Store examples map cleanly to Android app architecture.

## Kotlin Example

```kotlin
package com.mphocodes.androidpatterns.observer

data class StockUpdate(val sku: String, val available: Boolean)
fun interface StockObserver { fun onStockChanged(update: StockUpdate) }
class StockNotifier {
    private val observers = mutableSetOf<StockObserver>()
    fun subscribe(observer: StockObserver) { observers += observer }
    fun unsubscribe(observer: StockObserver) { observers -= observer }
    fun publish(update: StockUpdate) = observers.forEach { it.onStockChanged(update) }
}
```

## What To Notice

- The example uses Kotlin language features such as interfaces, data classes, objects, function interfaces, and expression bodies where they make the pattern clearer.
- The domain remains Apple Store-oriented, but the implementation is written as Android/Kotlin learning material.
- In a real Android app, keep these pattern roles behind package boundaries such as `domain`, `data`, and `presentation`.

## Practice Prompt

Adapt this pattern to a feature you know: a product details screen, cart flow, support journey, trade-in quote, or notification subscription.
