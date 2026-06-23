package com.mphocodes.androidpatterns.factorymethod

interface ShippingMethod {
    val title: String
    val deliveryEstimate: String
    fun shippingCost(weightKg: Double): Double
}
class InStorePickup : ShippingMethod {
    override val title = "In-store pickup"
    override val deliveryEstimate = "Same day"
    override fun shippingCost(weightKg: Double) = 0.0
}
class StandardShipping : ShippingMethod {
    override val title = "Standard shipping"
    override val deliveryEstimate = "3-5 business days"
    override fun shippingCost(weightKg: Double) = 0.0
}
class ExpressShipping(private val baseCost: Double) : ShippingMethod {
    override val title = "Express shipping"
    override val deliveryEstimate = "1-2 business days"
    override fun shippingCost(weightKg: Double) = baseCost * weightKg
}

enum class ShippingType { IN_STORE, STANDARD, EXPRESS }
object ShippingMethodFactory {
    fun create(type: ShippingType): ShippingMethod = when (type) {
        ShippingType.IN_STORE -> InStorePickup()
        ShippingType.STANDARD -> StandardShipping()
        ShippingType.EXPRESS -> ExpressShipping(baseCost = 5.0)
    }
}
