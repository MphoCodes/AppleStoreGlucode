package com.mphocodes.androidpatterns.templatemethod

data class WatchConfigurationRequest(
    val caseSize: String,
    val caseMaterial: String,
    val band: String,
    val bandSize: String,
    val engraving: String?
)

abstract class AppleWatchConfigurationMapper {
    fun process(): WatchConfigurationRequest = WatchConfigurationRequest(
        caseSize = mapCaseSize(),
        caseMaterial = mapCaseMaterial(),
        band = mapBand(),
        bandSize = mapBandSize(),
        engraving = mapEngraving()
    )

    protected abstract fun mapCaseSize(): String
    protected open fun mapCaseMaterial(): String = "Aluminium"
    protected abstract fun mapBand(): String
    protected open fun mapBandSize(): String = "Medium"
    protected open fun mapEngraving(): String? = null
}

class Series10AppleWatchMapper : AppleWatchConfigurationMapper() {
    override fun mapCaseSize() = "46mm"
    override fun mapCaseMaterial() = "Titanium"
    override fun mapBand() = "Sport Band"
    override fun mapBandSize() = "Large"
    override fun mapEngraving() = "Happy birthday"
}

class HermesSeries10AppleWatchMapper : AppleWatchConfigurationMapper() {
    override fun mapCaseSize() = "46mm"
    override fun mapCaseMaterial() = "Titanium"
    override fun mapBand() = "Hermes Kilim"
    override fun mapBandSize() = "Medium"
    override fun mapEngraving(): String? = null
}
