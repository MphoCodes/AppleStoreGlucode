package com.mphocodes.androidpatterns.builder

data class MacConfiguration(
    val model: String,
    val memoryGb: Int,
    val storageGb: Int,
    val appleCare: Boolean,
    val accessories: List<String>
)

class MacConfigurationBuilder(private val model: String) {
    private var memoryGb = 8
    private var storageGb = 256
    private var appleCare = false
    private val accessories = mutableListOf<String>()

    fun memory(gb: Int) = apply { memoryGb = gb }
    fun storage(gb: Int) = apply { storageGb = gb }
    fun withAppleCare() = apply { appleCare = true }
    fun addAccessory(name: String) = apply { accessories += name }
    fun build() = MacConfiguration(model, memoryGb, storageGb, appleCare, accessories.toList())
}
