package com.mphocodes.androidpatterns.adapter

data class LegacyInventoryItem(val sku: String, val stockCount: Int)
class LegacyInventoryClient { fun fetchSku(sku: String) = LegacyInventoryItem(sku, stockCount = 7) }

data class InventoryStatus(val sku: String, val availableUnits: Int, val canShip: Boolean)
interface InventoryRepository { fun statusFor(sku: String): InventoryStatus }

class LegacyInventoryAdapter(private val client: LegacyInventoryClient) : InventoryRepository {
    override fun statusFor(sku: String): InventoryStatus {
        val item = client.fetchSku(sku)
        return InventoryStatus(item.sku, item.stockCount, item.stockCount > 0)
    }
}
