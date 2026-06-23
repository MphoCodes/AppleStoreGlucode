package com.mphocodes.androidpatterns.bridge

interface PaymentProcessor { fun charge(amount: Double): String }
class CardProcessor : PaymentProcessor { override fun charge(amount: Double) = "Charged R$amount by card" }
class WalletProcessor : PaymentProcessor { override fun charge(amount: Double) = "Charged R$amount by wallet" }

abstract class CheckoutFlow(private val processor: PaymentProcessor) {
    fun pay(amount: Double): String = "${flowName()}: ${processor.charge(amount)}"
    protected abstract fun flowName(): String
}
class GuestCheckout(processor: PaymentProcessor) : CheckoutFlow(processor) { override fun flowName() = "Guest" }
class AccountCheckout(processor: PaymentProcessor) : CheckoutFlow(processor) { override fun flowName() = "Account" }
