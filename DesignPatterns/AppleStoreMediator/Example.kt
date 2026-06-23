package com.mphocodes.androidpatterns.mediator

data class ProductConfiguration(
    val processor: String,
    val memoryGb: Int,
    val storageGb: Int,
    val price: Double,
    val deliveryDays: Int
)

interface ProductConfigurationMediator {
    fun configurationChanged(configuration: ProductConfiguration)
}

class ProductConfigurationViewModel {
    var mediator: ProductConfigurationMediator? = null

    fun userSelected(configuration: ProductConfiguration) {
        mediator?.configurationChanged(configuration)
    }
}

class ProductSummaryViewModel {
    var total: Double = 0.0
        private set

    fun updateTotal(total: Double) {
        this.total = total
    }
}

class DeliveryEstimatorViewModel {
    var deliveryEstimate: String = "Not calculated"
        private set

    fun updateEstimate(days: Int) {
        deliveryEstimate = "$days-day delivery"
    }
}

class MacBookProductMediator(
    private val summaryViewModel: ProductSummaryViewModel,
    private val deliveryViewModel: DeliveryEstimatorViewModel
) : ProductConfigurationMediator {
    override fun configurationChanged(configuration: ProductConfiguration) {
        summaryViewModel.updateTotal(configuration.price)
        deliveryViewModel.updateEstimate(configuration.deliveryDays)
    }
}
