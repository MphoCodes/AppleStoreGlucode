package com.mphocodes.androidpatterns.strategy

interface DiscountStrategy { fun discountFor(subtotal: Double): Double }
class NoDiscount : DiscountStrategy { override fun discountFor(subtotal: Double) = 0.0 }
class StudentDiscount : DiscountStrategy { override fun discountFor(subtotal: Double) = subtotal * 0.1 }
class TradeInDiscount(private val tradeInValue: Double) : DiscountStrategy { override fun discountFor(subtotal: Double) = tradeInValue.coerceAtMost(subtotal) }
class PriceCalculator(private val strategy: DiscountStrategy) { fun total(subtotal: Double) = subtotal - strategy.discountFor(subtotal) }
