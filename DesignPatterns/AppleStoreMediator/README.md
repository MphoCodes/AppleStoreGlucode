# Mediator

## Intent

Centralize communication between related objects so they do not need direct references to each other.

## Pattern Overview

- The Mediator pattern coordinates communication between multiple related objects in a system.
- It promotes loose coupling by keeping components from calling each other directly.
- The mediator becomes the place where cross-component coordination lives.

## Problem Statement

When configuring a MacBook for purchase, several screen components need to stay in sync:

- Product configuration handles processor, memory, and storage selections.
- Price summary recalculates the total price.
- Delivery estimator updates delivery timing based on the selected configuration.

If each component talks directly to the others, the screen becomes tightly coupled and hard to change. A mediator lets the configuration component report one event, then coordinates the price and delivery updates without those components knowing about each other.

## Android/Kotlin Use Cases

- Coordinating multiple ViewModels or state holders on one complex screen.
- Keeping product configuration, totals, and delivery estimates synchronized.
- Routing UI events without making every component depend on every other component.

## Kotlin Example

```kotlin
package com.mphocodes.androidpatterns.mediator

data class ProductConfiguration(
    val processor: String,
    val memoryGb: Int,
    val storageGb: Int,
    val price: Double,
    val deliveryDays: Int
)

interface ProductConfigurationMediator {
    fun configurationChanged(configuration: ProductConfiguration)
}

class ProductConfigurationViewModel {
    var mediator: ProductConfigurationMediator? = null

    fun userSelected(configuration: ProductConfiguration) {
        mediator?.configurationChanged(configuration)
    }
}

class ProductSummaryViewModel {
    var total: Double = 0.0
        private set

    fun updateTotal(total: Double) {
        this.total = total
    }
}

class DeliveryEstimatorViewModel {
    var deliveryEstimate: String = "Not calculated"
        private set

    fun updateEstimate(days: Int) {
        deliveryEstimate = "$days-day delivery"
    }
}

class MacBookProductMediator(
    private val summaryViewModel: ProductSummaryViewModel,
    private val deliveryViewModel: DeliveryEstimatorViewModel
) : ProductConfigurationMediator {
    override fun configurationChanged(configuration: ProductConfiguration) {
        summaryViewModel.updateTotal(configuration.price)
        deliveryViewModel.updateEstimate(configuration.deliveryDays)
    }
}
```

## Example Usage

```kotlin
val configurationViewModel = ProductConfigurationViewModel()
val summaryViewModel = ProductSummaryViewModel()
val deliveryViewModel = DeliveryEstimatorViewModel()

configurationViewModel.mediator = MacBookProductMediator(
    summaryViewModel = summaryViewModel,
    deliveryViewModel = deliveryViewModel
)

configurationViewModel.userSelected(
    ProductConfiguration(
        processor = "M4 Pro",
        memoryGb = 24,
        storageGb = 512,
        price = 2599.99,
        deliveryDays = 3
    )
)

println(summaryViewModel.total) // 2599.99
println(deliveryViewModel.deliveryEstimate) // 3-day delivery
```

## What To Notice

- `ProductConfigurationViewModel` only knows about the mediator contract.
- `ProductSummaryViewModel` and `DeliveryEstimatorViewModel` do not know about the configuration component.
- The coordination rule is easy to test because it lives in `MacBookProductMediator`.

## Practice Prompt

Apply this pattern to a checkout screen where changing the shipping address updates tax, delivery options, and payment eligibility.
