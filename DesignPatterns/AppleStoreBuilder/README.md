# Builder

## Intent

Construct a complex object step by step while keeping call sites readable.

## Android/Kotlin Use Cases

- ViewModel and UI-state behavior that changes by product, account, checkout, or order state.
- Repository, service, and use-case boundaries that need testable contracts.
- Checkout, inventory, recommendation, analytics, and support flows where Apple Store examples map cleanly to Android app architecture.

## Kotlin Example

```kotlin
package com.mphocodes.androidpatterns.builder

data class MacConfiguration(
    val model: String,
    val memoryGb: Int,
    val storageGb: Int,
    val appleCare: Boolean,
    val accessories: List<String>
)

class MacConfigurationBuilder(private val model: String) {
    private var memoryGb = 8
    private var storageGb = 256
    private var appleCare = false
    private val accessories = mutableListOf<String>()

    fun memory(gb: Int) = apply { memoryGb = gb }
    fun storage(gb: Int) = apply { storageGb = gb }
    fun withAppleCare() = apply { appleCare = true }
    fun addAccessory(name: String) = apply { accessories += name }
    fun build() = MacConfiguration(model, memoryGb, storageGb, appleCare, accessories.toList())
}
```

## What To Notice

- The example uses Kotlin language features such as interfaces, data classes, objects, function interfaces, and expression bodies where they make the pattern clearer.
- The domain remains Apple Store-oriented, but the implementation is written as Android/Kotlin learning material.
- In a real Android app, keep these pattern roles behind package boundaries such as `domain`, `data`, and `presentation`.

## Practice Prompt

Adapt this pattern to a feature you know: a product details screen, cart flow, support journey, trade-in quote, or notification subscription.
