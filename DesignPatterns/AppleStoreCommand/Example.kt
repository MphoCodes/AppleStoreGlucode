package com.mphocodes.androidpatterns.command

interface CartCommand { fun execute(cart: ShoppingCart) }
class ShoppingCart {
    private val items = mutableListOf<String>()
    fun add(sku: String) { items += sku }
    fun remove(sku: String) { items -= sku }
    fun snapshot(): List<String> = items.toList()
}
class AddToCart(private val sku: String) : CartCommand { override fun execute(cart: ShoppingCart) = cart.add(sku) }
class RemoveFromCart(private val sku: String) : CartCommand { override fun execute(cart: ShoppingCart) = cart.remove(sku) }
class CartCommandQueue { fun run(cart: ShoppingCart, commands: List<CartCommand>) = commands.forEach { it.execute(cart) } }
