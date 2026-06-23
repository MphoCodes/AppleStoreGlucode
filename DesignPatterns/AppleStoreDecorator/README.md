# Decorator

## Intent

Add behavior by wrapping an object instead of changing its class.

## Android/Kotlin Use Cases

- ViewModel and UI-state behavior that changes by product, account, checkout, or order state.
- Repository, service, and use-case boundaries that need testable contracts.
- Checkout, inventory, recommendation, analytics, and support flows where Apple Store examples map cleanly to Android app architecture.

## Kotlin Example

```kotlin
package com.mphocodes.androidpatterns.decorator

interface OrderLine { fun description(): String; fun total(): Double }
class ProductLine(private val name: String, private val price: Double) : OrderLine {
    override fun description() = name
    override fun total() = price
}
class AppleCareDecorator(private val line: OrderLine) : OrderLine {
    override fun description() = "${line.description()} + AppleCare"
    override fun total() = line.total() + 199.0
}
class GiftWrapDecorator(private val line: OrderLine) : OrderLine {
    override fun description() = "${line.description()} + gift wrap"
    override fun total() = line.total() + 9.99
}
```

## What To Notice

- The example uses Kotlin language features such as interfaces, data classes, objects, function interfaces, and expression bodies where they make the pattern clearer.
- The domain remains Apple Store-oriented, but the implementation is written as Android/Kotlin learning material.
- In a real Android app, keep these pattern roles behind package boundaries such as `domain`, `data`, and `presentation`.

## Practice Prompt

Adapt this pattern to a feature you know: a product details screen, cart flow, support journey, trade-in quote, or notification subscription.
