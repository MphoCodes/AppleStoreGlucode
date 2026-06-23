package com.mphocodes.androidpatterns.abstractfactory

interface ProductCard { fun render(productName: String): String }
interface PurchaseButton { fun label(): String }

class PhoneProductCard : ProductCard {
    override fun render(productName: String) = "Phone card: $productName with trade-in messaging"
}
class PhonePurchaseButton : PurchaseButton { override fun label() = "Buy iPhone" }
class WatchProductCard : ProductCard {
    override fun render(productName: String) = "Watch card: $productName with band picker"
}
class WatchPurchaseButton : PurchaseButton { override fun label() = "Buy Apple Watch" }

interface StoreUiFactory {
    fun productCard(): ProductCard
    fun purchaseButton(): PurchaseButton
}
class PhoneStoreUiFactory : StoreUiFactory {
    override fun productCard() = PhoneProductCard()
    override fun purchaseButton() = PhonePurchaseButton()
}
class WatchStoreUiFactory : StoreUiFactory {
    override fun productCard() = WatchProductCard()
    override fun purchaseButton() = WatchPurchaseButton()
}

fun buildStoreScreen(factory: StoreUiFactory, productName: String): List<String> = listOf(
    factory.productCard().render(productName),
    factory.purchaseButton().label()
)
