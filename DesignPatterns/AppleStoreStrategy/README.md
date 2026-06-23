# Strategy

## Intent

Choose an interchangeable algorithm at runtime.

## Android/Kotlin Use Cases

- ViewModel and UI-state behavior that changes by product, account, checkout, or order state.
- Repository, service, and use-case boundaries that need testable contracts.
- Checkout, inventory, recommendation, analytics, and support flows where Apple Store examples map cleanly to Android app architecture.

## Kotlin Example

```kotlin
package com.mphocodes.androidpatterns.strategy

interface DiscountStrategy { fun discountFor(subtotal: Double): Double }
class NoDiscount : DiscountStrategy { override fun discountFor(subtotal: Double) = 0.0 }
class StudentDiscount : DiscountStrategy { override fun discountFor(subtotal: Double) = subtotal * 0.1 }
class TradeInDiscount(private val tradeInValue: Double) : DiscountStrategy { override fun discountFor(subtotal: Double) = tradeInValue.coerceAtMost(subtotal) }
class PriceCalculator(private val strategy: DiscountStrategy) { fun total(subtotal: Double) = subtotal - strategy.discountFor(subtotal) }
```

## What To Notice

- The example uses Kotlin language features such as interfaces, data classes, objects, function interfaces, and expression bodies where they make the pattern clearer.
- The domain remains Apple Store-oriented, but the implementation is written as Android/Kotlin learning material.
- In a real Android app, keep these pattern roles behind package boundaries such as `domain`, `data`, and `presentation`.

## Practice Prompt

Adapt this pattern to a feature you know: a product details screen, cart flow, support journey, trade-in quote, or notification subscription.
