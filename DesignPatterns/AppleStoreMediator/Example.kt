package com.mphocodes.androidpatterns.mediator

class ProductPicker(private val mediator: StoreMediator) { fun select(sku: String) = mediator.productSelected(sku) }
class PricePanel { fun showPrice(sku: String) = "Showing price for $sku" }
class RecommendationPanel { fun refreshFor(sku: String) = "Refreshing recommendations for $sku" }
class StoreMediator(private val pricePanel: PricePanel, private val recommendations: RecommendationPanel) {
    fun productSelected(sku: String): List<String> = listOf(pricePanel.showPrice(sku), recommendations.refreshFor(sku))
}
