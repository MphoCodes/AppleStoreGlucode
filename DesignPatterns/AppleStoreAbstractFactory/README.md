# Abstract Factory

## Intent

Create families of related objects without binding callers to concrete classes.

## Problem Statement

Apple Store product areas need matching UI parts. A phone product area may need trade-in messaging and an iPhone purchase action, while a watch product area may need a band picker and an Apple Watch purchase action.

Without Abstract Factory, the screen builder would need `when` branches spread across the UI to decide which product card, purchase button, comparison module, and accessory selector belong together. That makes it easy to mix incompatible components. Abstract Factory moves each compatible family behind one factory.

## Android/Kotlin Use Cases

- ViewModel and UI-state behavior that changes by product, account, checkout, or order state.
- Repository, service, and use-case boundaries that need testable contracts.
- Checkout, inventory, recommendation, analytics, and support flows where Apple Store examples map cleanly to Android app architecture.
- UI families where several components must vary together, such as iPhone, Mac, Watch, and iPad purchase experiences.

## Kotlin Example

```kotlin
package com.mphocodes.androidpatterns.abstractfactory

interface ProductCard { fun render(productName: String): String }
interface PurchaseButton { fun label(): String }

class PhoneProductCard : ProductCard {
    override fun render(productName: String) = "Phone card: $productName with trade-in messaging"
}
class PhonePurchaseButton : PurchaseButton { override fun label() = "Buy iPhone" }
class WatchProductCard : ProductCard {
    override fun render(productName: String) = "Watch card: $productName with band picker"
}
class WatchPurchaseButton : PurchaseButton { override fun label() = "Buy Apple Watch" }

interface StoreUiFactory {
    fun productCard(): ProductCard
    fun purchaseButton(): PurchaseButton
}
class PhoneStoreUiFactory : StoreUiFactory {
    override fun productCard() = PhoneProductCard()
    override fun purchaseButton() = PhonePurchaseButton()
}
class WatchStoreUiFactory : StoreUiFactory {
    override fun productCard() = WatchProductCard()
    override fun purchaseButton() = WatchPurchaseButton()
}

fun buildStoreScreen(factory: StoreUiFactory, productName: String): List<String> = listOf(
    factory.productCard().render(productName),
    factory.purchaseButton().label()
)
```

## What To Notice

- The example uses Kotlin language features such as interfaces, data classes, objects, function interfaces, and expression bodies where they make the pattern clearer.
- The domain remains Apple Store-oriented, but the implementation is written as Android/Kotlin learning material.
- In a real Android app, keep these pattern roles behind package boundaries such as `domain`, `data`, and `presentation`.
- The factory returns related objects that are safe to use together; the caller does not construct individual concrete UI pieces directly.

## Practice Prompt

Adapt this pattern to a feature you know: a product details screen, cart flow, support journey, trade-in quote, or notification subscription.
