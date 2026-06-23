package com.mphocodes.androidpatterns.composite

interface StoreComponent { val name: String; fun price(): Double }
class Product(override val name: String, private val amount: Double) : StoreComponent { override fun price() = amount }
class Bundle(override val name: String, private val children: List<StoreComponent>) : StoreComponent {
    override fun price() = children.sumOf { it.price() }
}
