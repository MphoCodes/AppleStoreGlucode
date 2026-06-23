# Observer

## Intent

Notify interested objects automatically when a subject changes state.

## Pattern Overview

- Observer defines a one-to-many relationship between a subject and observers.
- The subject owns the state of interest.
- Observers subscribe to be notified when that state changes.
- This avoids repeated polling and keeps dependent state synchronized.

## Problem Statement

An Apple Store customer bag can change from several places: the bag screen, product details, another signed-in device, or a backend event. The bag list and top navigation badge should both reflect the same products.

Without Observer, each UI surface may fetch separately and drift out of sync. With Observer, a single bag notifier publishes changes to every subscribed UI state holder.

## Android/Kotlin Use Cases

- Keeping multiple UI state holders in sync with one domain source.
- Bridging WebSocket or push updates into app state.
- Updating badges, lists, and summaries from the same state change.
- Modeling simple callback subscriptions before moving to `Flow` or another reactive API.

## Kotlin Example

```kotlin
package com.mphocodes.androidpatterns.observer

data class Product(val name: String, val price: Double)

fun interface BagObserver {
    fun onBagChanged(products: List<Product>)
}

interface BagNotifier {
    fun attach(observer: BagObserver)
    fun detach(observer: BagObserver)
    fun notifyObservers()
}

class WebSocketBagNotifier : BagNotifier {
    private val observers = mutableSetOf<BagObserver>()
    private val products = mutableListOf<Product>()

    fun addProduct(product: Product) {
        products += product
        notifyObservers()
    }

    fun removeProduct(product: Product) {
        products -= product
        notifyObservers()
    }

    override fun attach(observer: BagObserver) {
        observers += observer
    }

    override fun detach(observer: BagObserver) {
        observers -= observer
    }

    override fun notifyObservers() {
        val snapshot = products.toList()
        observers.forEach { it.onBagChanged(snapshot) }
    }
}

class BagListViewModel(notifier: BagNotifier) : BagObserver {
    var products: List<Product> = emptyList()
        private set

    init {
        notifier.attach(this)
    }

    override fun onBagChanged(products: List<Product>) {
        this.products = products
    }
}

class BagIconViewModel(notifier: BagNotifier) : BagObserver {
    var badgeCount: Int = 0
        private set

    init {
        notifier.attach(this)
    }

    override fun onBagChanged(products: List<Product>) {
        badgeCount = products.size
    }
}
```

## Example Usage

```kotlin
val notifier = WebSocketBagNotifier()
val bagListViewModel = BagListViewModel(notifier)
val bagIconViewModel = BagIconViewModel(notifier)

notifier.addProduct(Product(name = "iPad Pro", price = 999.99))

println(bagListViewModel.products) // [Product(name=iPad Pro, price=999.99)]
println(bagIconViewModel.badgeCount) // 1
```

## What To Notice

- `WebSocketBagNotifier` is the subject.
- `BagListViewModel` and `BagIconViewModel` are observers.
- Observers receive immutable snapshots so they cannot mutate the subject's internal list.
- In production Android code, `StateFlow<List<Product>>` can often express this relationship with lifecycle-aware collection.

## Practice Prompt

Apply this pattern to stock availability so product details, checkout, and notifications all react to inventory changes.
