# Template Method

## Intent

Define the skeleton of an algorithm and let subclasses override selected steps.

## Pattern Fit Note

Template Method is an inheritance-based pattern. Use it when the sequence of steps must stay fixed and subclasses should customize only selected steps. If the steps can be freely composed or swapped, Strategy or plain composition may be clearer.

## Android/Kotlin Use Cases

- ViewModel and UI-state behavior that changes by product, account, checkout, or order state.
- Repository, service, and use-case boundaries that need testable contracts.
- Checkout, inventory, recommendation, analytics, and support flows where Apple Store examples map cleanly to Android app architecture.

## Kotlin Example

```kotlin
package com.mphocodes.androidpatterns.templatemethod

abstract class ProductLaunchChecklist {
    fun run(): List<String> = listOf(prepareInventory(), publishProductPage(), notifyCustomers())
    protected abstract fun prepareInventory(): String
    protected open fun publishProductPage() = "Published product page"
    protected abstract fun notifyCustomers(): String
}
class IPhoneLaunchChecklist : ProductLaunchChecklist() {
    override fun prepareInventory() = "Prepared iPhone inventory"
    override fun notifyCustomers() = "Sent iPhone launch notification"
}
```

## What To Notice

- The example uses Kotlin language features such as interfaces, data classes, objects, function interfaces, and expression bodies where they make the pattern clearer.
- The domain remains Apple Store-oriented, but the implementation is written as Android/Kotlin learning material.
- In a real Android app, keep these pattern roles behind package boundaries such as `domain`, `data`, and `presentation`.
- The template method should own the order of operations; subclasses should customize steps, not duplicate the whole algorithm.

## Practice Prompt

Adapt this pattern to a feature you know: a product details screen, cart flow, support journey, trade-in quote, or notification subscription.
