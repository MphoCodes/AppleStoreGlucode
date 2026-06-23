package com.mphocodes.androidpatterns.templatemethod

abstract class ProductLaunchChecklist {
    fun run(): List<String> = listOf(prepareInventory(), publishProductPage(), notifyCustomers())
    protected abstract fun prepareInventory(): String
    protected open fun publishProductPage() = "Published product page"
    protected abstract fun notifyCustomers(): String
}
class IPhoneLaunchChecklist : ProductLaunchChecklist() {
    override fun prepareInventory() = "Prepared iPhone inventory"
    override fun notifyCustomers() = "Sent iPhone launch notification"
}
