# Kotlin Style Guide Summary

This is a Kotlin-first companion to the design-pattern examples in this repo. It focuses on the conventions that make Kotlin examples readable, idiomatic, and useful as Android learning references.

## Clarity First

- Prefer names that explain the role of the value at the call site.
- Avoid clever abbreviations unless they are established in the domain.
- Keep examples small, but do not remove the detail needed to understand the pattern.
- Use domain names consistently: if the example is about a cart, keep terms like `Cart`, `CartItem`, `CartCommand`, and `ShoppingCart` aligned.

## Kotlin Naming

- Use `UpperCamelCase` for classes, interfaces, objects, enum classes, and type aliases.
- Use `lowerCamelCase` for functions, properties, parameters, and local variables.
- Use screaming snake case only for true constants:

```kotlin
const val MAX_RETRY_COUNT = 3
```

- Prefer capability names for interfaces when the role is behavioral:

```kotlin
interface ProductVisitorAccepting
interface DiscountStrategy
interface InventoryRepository
```

- Prefer noun names for domain value types:

```kotlin
data class Product
data class ShippingMethod
data class WatchConfigurationRequest
```

## Function Names

- Name functions for the action they perform:

```kotlin
fun addProduct(product: Product)
fun removeProduct(product: Product)
fun calculateTotal(): Double
```

- Use Boolean names that read as assertions:

```kotlin
val isAvailable: Boolean
fun canShipTo(countryCode: String): Boolean
```

- Avoid vague names like `doWork`, `handle`, or `process` unless the pattern itself requires a general method name. When `process()` is used as a Template Method, document what sequence it owns.

## Properties And Immutability

- Prefer `val` over `var` by default.
- Expose immutable snapshots from mutable internals:

```kotlin
class Cart {
    private val items = mutableListOf<CartItem>()

    fun snapshot(): List<CartItem> = items.toList()
}
```

- Use `private set` when outside code may read state but only the owner should mutate it:

```kotlin
var currentIndex: Int = 0
    private set
```

## Data Classes

Use `data class` for value objects that primarily carry state:

```kotlin
data class ProductConfiguration(
    val processor: String,
    val memoryGb: Int,
    val storageGb: Int
)
```

Avoid putting large behavior into data classes. Move behavior to strategies, visitors, mappers, or use cases when that keeps roles clearer.

## Interfaces

Use interfaces for replaceable behavior:

```kotlin
interface ShippingMethod {
    val title: String
    fun shippingCost(weightKg: Double): Double
}
```

Use `fun interface` when the contract has exactly one abstract method and is meant to support lambda-style usage:

```kotlin
fun interface BagObserver {
    fun onBagChanged(products: List<Product>)
}
```

## Objects

Use `object` when a single process-wide instance is intentional:

```kotlin
object StoreAnalytics {
    fun track(event: String) = Unit
}
```

Avoid using `object` only to avoid dependency wiring. Global state can make tests and examples harder to reason about.

## Nullability

- Prefer non-null types unless absence is part of the model.
- Use nullable return types when the result may genuinely be missing:

```kotlin
fun mapEngraving(): String? = null
```

- Avoid using nullable fields where an empty collection, null object, or sealed state is clearer.

## Android-Oriented Guidance

- Keep framework types out of domain examples unless the Android boundary is the point of the example.
- Use ViewModel-style names in docs when explaining presentation responsibilities, but keep examples compile-friendly as plain Kotlin.
- Prefer `StateFlow` or immutable UI state models in production Android apps when observing changing state.
- Keep repository interfaces stable and adapt external service responses behind them.

## Design Pattern Example Rules

- Name the pattern roles explicitly in the README.
- Keep product-domain names realistic enough to teach the pattern.
- Make the Kotlin snippet and `Example.kt` match.
- Do not add a pattern if a direct function or small class would be clearer.
- Explain when the pattern is a poor fit, not only when it is useful.
