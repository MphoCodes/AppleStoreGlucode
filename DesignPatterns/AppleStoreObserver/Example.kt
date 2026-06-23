package com.mphocodes.androidpatterns.observer

data class Product(val name: String, val price: Double)

fun interface BagObserver {
    fun onBagChanged(products: List<Product>)
}

interface BagNotifier {
    fun attach(observer: BagObserver)
    fun detach(observer: BagObserver)
    fun notifyObservers()
}

class WebSocketBagNotifier : BagNotifier {
    private val observers = mutableSetOf<BagObserver>()
    private val products = mutableListOf<Product>()

    fun addProduct(product: Product) {
        products += product
        notifyObservers()
    }

    fun removeProduct(product: Product) {
        products -= product
        notifyObservers()
    }

    override fun attach(observer: BagObserver) {
        observers += observer
    }

    override fun detach(observer: BagObserver) {
        observers -= observer
    }

    override fun notifyObservers() {
        val snapshot = products.toList()
        observers.forEach { it.onBagChanged(snapshot) }
    }
}

class BagListViewModel(notifier: BagNotifier) : BagObserver {
    var products: List<Product> = emptyList()
        private set

    init {
        notifier.attach(this)
    }

    override fun onBagChanged(products: List<Product>) {
        this.products = products
    }
}

class BagIconViewModel(notifier: BagNotifier) : BagObserver {
    var badgeCount: Int = 0
        private set

    init {
        notifier.attach(this)
    }

    override fun onBagChanged(products: List<Product>) {
        badgeCount = products.size
    }
}
