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
