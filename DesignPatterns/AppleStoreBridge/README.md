# Bridge

## Intent

Separate an abstraction from its platform or implementation detail so both can vary independently.

## Android/Kotlin Use Cases

- ViewModel and UI-state behavior that changes by product, account, checkout, or order state.
- Repository, service, and use-case boundaries that need testable contracts.
- Checkout, inventory, recommendation, analytics, and support flows where Apple Store examples map cleanly to Android app architecture.

## Kotlin Example

```kotlin
package com.mphocodes.androidpatterns.bridge

interface PaymentProcessor { fun charge(amount: Double): String }
class CardProcessor : PaymentProcessor { override fun charge(amount: Double) = "Charged R$amount by card" }
class WalletProcessor : PaymentProcessor { override fun charge(amount: Double) = "Charged R$amount by wallet" }

abstract class CheckoutFlow(private val processor: PaymentProcessor) {
    fun pay(amount: Double): String = "${flowName()}: ${processor.charge(amount)}"
    protected abstract fun flowName(): String
}
class GuestCheckout(processor: PaymentProcessor) : CheckoutFlow(processor) { override fun flowName() = "Guest" }
class AccountCheckout(processor: PaymentProcessor) : CheckoutFlow(processor) { override fun flowName() = "Account" }
```

## What To Notice

- The example uses Kotlin language features such as interfaces, data classes, objects, function interfaces, and expression bodies where they make the pattern clearer.
- The domain remains Apple Store-oriented, but the implementation is written as Android/Kotlin learning material.
- In a real Android app, keep these pattern roles behind package boundaries such as `domain`, `data`, and `presentation`.

## Practice Prompt

Adapt this pattern to a feature you know: a product details screen, cart flow, support journey, trade-in quote, or notification subscription.
