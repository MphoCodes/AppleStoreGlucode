# State

## Intent

Move state-specific behavior into separate objects so behavior changes when state changes.

## Android/Kotlin Use Cases

- ViewModel and UI-state behavior that changes by product, account, checkout, or order state.
- Repository, service, and use-case boundaries that need testable contracts.
- Checkout, inventory, recommendation, analytics, and support flows where Apple Store examples map cleanly to Android app architecture.

## Kotlin Example

```kotlin
package com.mphocodes.androidpatterns.state

interface OrderState { fun label(): String; fun next(): OrderState }
class DraftOrder : OrderState { override fun label() = "Draft"; override fun next() = PaidOrder() }
class PaidOrder : OrderState { override fun label() = "Paid"; override fun next() = ShippedOrder() }
class ShippedOrder : OrderState { override fun label() = "Shipped"; override fun next() = this }
class Order { private var state: OrderState = DraftOrder(); fun advance() { state = state.next() }; fun status() = state.label() }
```

## What To Notice

- The example uses Kotlin language features such as interfaces, data classes, objects, function interfaces, and expression bodies where they make the pattern clearer.
- The domain remains Apple Store-oriented, but the implementation is written as Android/Kotlin learning material.
- In a real Android app, keep these pattern roles behind package boundaries such as `domain`, `data`, and `presentation`.

## Practice Prompt

Adapt this pattern to a feature you know: a product details screen, cart flow, support journey, trade-in quote, or notification subscription.
