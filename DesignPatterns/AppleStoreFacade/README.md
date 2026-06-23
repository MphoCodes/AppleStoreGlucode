# Facade

## Intent

Provide one simple entry point over several collaborating services.

## Android/Kotlin Use Cases

- ViewModel and UI-state behavior that changes by product, account, checkout, or order state.
- Repository, service, and use-case boundaries that need testable contracts.
- Checkout, inventory, recommendation, analytics, and support flows where Apple Store examples map cleanly to Android app architecture.

## Kotlin Example

```kotlin
package com.mphocodes.androidpatterns.facade

class InventoryService { fun reserve(sku: String) = "Reserved $sku" }
class PaymentService { fun charge(amount: Double) = "Charged R$amount" }
class FulfilmentService { fun ship(sku: String) = "Shipment created for $sku" }
class CheckoutFacade(
    private val inventory: InventoryService,
    private val payment: PaymentService,
    private val fulfilment: FulfilmentService
) {
    fun checkout(sku: String, amount: Double): List<String> = listOf(
        inventory.reserve(sku), payment.charge(amount), fulfilment.ship(sku)
    )
}
```

## What To Notice

- The example uses Kotlin language features such as interfaces, data classes, objects, function interfaces, and expression bodies where they make the pattern clearer.
- The domain remains Apple Store-oriented, but the implementation is written as Android/Kotlin learning material.
- In a real Android app, keep these pattern roles behind package boundaries such as `domain`, `data`, and `presentation`.

## Practice Prompt

Adapt this pattern to a feature you know: a product details screen, cart flow, support journey, trade-in quote, or notification subscription.
