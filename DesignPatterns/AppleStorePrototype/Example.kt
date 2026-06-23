package com.mphocodes.androidpatterns.prototype

data class ProductListing(
    val sku: String,
    val name: String,
    val badges: List<String>,
    val highlighted: Boolean
)

fun duplicateForEducationStore(base: ProductListing): ProductListing = base.copy(
    sku = "EDU-${base.sku}",
    badges = base.badges + "Education pricing"
)
