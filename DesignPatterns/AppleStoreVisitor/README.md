# Visitor

## Intent

Add operations over a set of object types without changing those object classes.

## Problem Statement

Visitor is useful when the operation is not core to the product model. A temporary education discount, employee discount, export format, or analytics description may need to operate over products without becoming permanent product behavior.

If the operation is central to the product itself, put it on the product. If it is a temporary or cross-cutting operation that changes independently, a visitor can keep the model smaller.

## Android/Kotlin Use Cases

- ViewModel and UI-state behavior that changes by product, account, checkout, or order state.
- Repository, service, and use-case boundaries that need testable contracts.
- Checkout, inventory, recommendation, analytics, and support flows where Apple Store examples map cleanly to Android app architecture.

## Kotlin Example

```kotlin
package com.mphocodes.androidpatterns.visitor

interface StoreItem { fun accept(visitor: StoreItemVisitor): String }
class Phone(private val name: String) : StoreItem { override fun accept(visitor: StoreItemVisitor) = visitor.visitPhone(name) }
class ServicePlan(private val name: String) : StoreItem { override fun accept(visitor: StoreItemVisitor) = visitor.visitServicePlan(name) }
interface StoreItemVisitor { fun visitPhone(name: String): String; fun visitServicePlan(name: String): String }
class AccessibilityLabelVisitor : StoreItemVisitor {
    override fun visitPhone(name: String) = "Phone product: $name"
    override fun visitServicePlan(name: String) = "Service plan: $name"
}
```

## What To Notice

- The example uses Kotlin language features such as interfaces, data classes, objects, function interfaces, and expression bodies where they make the pattern clearer.
- The domain remains Apple Store-oriented, but the implementation is written as Android/Kotlin learning material.
- In a real Android app, keep these pattern roles behind package boundaries such as `domain`, `data`, and `presentation`.
- Keep element data and visitor acceptance separate when that makes the roles clearer.

## Practice Prompt

Adapt this pattern to a feature you know: a product details screen, cart flow, support journey, trade-in quote, or notification subscription.
