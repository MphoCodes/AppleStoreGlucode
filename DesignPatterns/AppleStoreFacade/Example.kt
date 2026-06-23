package com.mphocodes.androidpatterns.facade

class InventoryService { fun reserve(sku: String) = "Reserved $sku" }
class PaymentService { fun charge(amount: Double) = "Charged R$amount" }
class FulfilmentService { fun ship(sku: String) = "Shipment created for $sku" }
class CheckoutFacade(
    private val inventory: InventoryService,
    private val payment: PaymentService,
    private val fulfilment: FulfilmentService
) {
    fun checkout(sku: String, amount: Double): List<String> = listOf(
        inventory.reserve(sku), payment.charge(amount), fulfilment.ship(sku)
    )
}
