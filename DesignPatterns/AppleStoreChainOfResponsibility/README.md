# Chain of Responsibility

## Intent

Pass a request through handlers until one can process it.

## Android/Kotlin Use Cases

- ViewModel and UI-state behavior that changes by product, account, checkout, or order state.
- Repository, service, and use-case boundaries that need testable contracts.
- Checkout, inventory, recommendation, analytics, and support flows where Apple Store examples map cleanly to Android app architecture.

## Kotlin Example

```kotlin
package com.mphocodes.androidpatterns.chain

data class SupportRequest(val topic: String, val severity: Int)
interface SupportHandler {
    var next: SupportHandler?
    fun handle(request: SupportRequest): String?
    fun pass(request: SupportRequest) = handle(request) ?: next?.pass(request)
}
class StoreStaff : SupportHandler {
    override var next: SupportHandler? = null
    override fun handle(request: SupportRequest) = if (request.severity <= 1) "Store staff resolved ${request.topic}" else null
}
class GeniusBar : SupportHandler {
    override var next: SupportHandler? = null
    override fun handle(request: SupportRequest) = if (request.severity <= 3) "Genius Bar resolved ${request.topic}" else null
}
class EngineeringSupport : SupportHandler {
    override var next: SupportHandler? = null
    override fun handle(request: SupportRequest) = "Engineering reviewed ${request.topic}"
}
```

## What To Notice

- The example uses Kotlin language features such as interfaces, data classes, objects, function interfaces, and expression bodies where they make the pattern clearer.
- The domain remains Apple Store-oriented, but the implementation is written as Android/Kotlin learning material.
- In a real Android app, keep these pattern roles behind package boundaries such as `domain`, `data`, and `presentation`.

## Practice Prompt

Adapt this pattern to a feature you know: a product details screen, cart flow, support journey, trade-in quote, or notification subscription.
