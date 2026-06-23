# Visitor

## Intent

Add operations to existing object types without changing those object types.

## Pattern Overview

- Visitor is useful when you need behavior that is not core to the element type.
- Elements accept visitors.
- Visitors implement operations over those elements.
- New operations can be added by creating new visitors.

## Problem Statement

Apple Store products may temporarily support different discount calculations, such as education discounts or employee discounts. Discounts can change over time and new discount types may be added.

Putting every temporary discount rule inside every product class would make product models noisy and violate the open/closed principle. Visitor lets product types remain focused while discount visitors provide additional operations.

## Android/Kotlin Use Cases

- Applying temporary calculations over stable domain models.
- Creating export, analytics, or description operations without editing model classes.
- Keeping promotional logic separate from product data.

## Kotlin Example

```kotlin
package com.mphocodes.androidpatterns.visitor

interface Product {
    val id: String
    val price: Double
}

interface ProductVisitor<R> {
    fun visit(product: Product): R
}

interface ProductVisitorAccepting {
    fun <R> accept(visitor: ProductVisitor<R>): R
}

data class MacBookProProduct(
    override val id: String,
    override val price: Double
) : Product, ProductVisitorAccepting {
    override fun <R> accept(visitor: ProductVisitor<R>): R = visitor.visit(this)
}

data class VisionProProduct(
    override val id: String,
    override val price: Double
) : Product, ProductVisitorAccepting {
    override fun <R> accept(visitor: ProductVisitor<R>): R = visitor.visit(this)
}

class EducationDiscountVisitor : ProductVisitor<Double> {
    override fun visit(product: Product): Double = product.price * 0.25
}

class EmployeeDiscountVisitor : ProductVisitor<Double> {
    override fun visit(product: Product): Double = product.price * 0.5
}

class DescriptionVisitor : ProductVisitor<String> {
    override fun visit(product: Product): String =
        "Product with ID ${product.id} costs ${product.price}"
}
```

## Example Usage

```kotlin
val macBook = MacBookProProduct(id = "mac-1", price = 1000.0)
val visionPro = VisionProProduct(id = "vision-1", price = 10000.0)

val educationDiscount = macBook.accept(EducationDiscountVisitor())
val employeeDiscount = visionPro.accept(EmployeeDiscountVisitor())
val description = macBook.accept(DescriptionVisitor())

println(educationDiscount) // 250.0
println(employeeDiscount) // 5000.0
println(description) // Product with ID mac-1 costs 1000.0
```

## What To Notice

- `Product` exposes product data.
- `ProductVisitorAccepting` exposes the visitor entry point.
- Discount logic is outside the product classes.
- A new discount type can be added as another visitor without editing `MacBookProProduct` or `VisionProProduct`.

## Practice Prompt

Add a `TaxEstimateVisitor` that calculates estimated tax for different products without modifying the product data classes.
