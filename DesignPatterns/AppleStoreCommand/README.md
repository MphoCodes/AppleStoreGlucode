# Command

## Intent

Encapsulate an action as an object so it can be queued, retried, or undone.

## Android/Kotlin Use Cases

- ViewModel and UI-state behavior that changes by product, account, checkout, or order state.
- Repository, service, and use-case boundaries that need testable contracts.
- Checkout, inventory, recommendation, analytics, and support flows where Apple Store examples map cleanly to Android app architecture.

## Kotlin Example

```kotlin
package com.mphocodes.androidpatterns.command

interface CartCommand { fun execute(cart: ShoppingCart) }
class ShoppingCart {
    private val items = mutableListOf<String>()
    fun add(sku: String) { items += sku }
    fun remove(sku: String) { items -= sku }
    fun snapshot(): List<String> = items.toList()
}
class AddToCart(private val sku: String) : CartCommand { override fun execute(cart: ShoppingCart) = cart.add(sku) }
class RemoveFromCart(private val sku: String) : CartCommand { override fun execute(cart: ShoppingCart) = cart.remove(sku) }
class CartCommandQueue { fun run(cart: ShoppingCart, commands: List<CartCommand>) = commands.forEach { it.execute(cart) } }
```

## What To Notice

- The example uses Kotlin language features such as interfaces, data classes, objects, function interfaces, and expression bodies where they make the pattern clearer.
- The domain remains Apple Store-oriented, but the implementation is written as Android/Kotlin learning material.
- In a real Android app, keep these pattern roles behind package boundaries such as `domain`, `data`, and `presentation`.

## Practice Prompt

Adapt this pattern to a feature you know: a product details screen, cart flow, support journey, trade-in quote, or notification subscription.
