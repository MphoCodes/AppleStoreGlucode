package com.mphocodes.androidpatterns.chain

data class SupportRequest(val topic: String, val severity: Int)
interface SupportHandler {
    var next: SupportHandler?
    fun handle(request: SupportRequest): String?
    fun pass(request: SupportRequest) = handle(request) ?: next?.pass(request)
}
class StoreStaff : SupportHandler {
    override var next: SupportHandler? = null
    override fun handle(request: SupportRequest) = if (request.severity <= 1) "Store staff resolved ${request.topic}" else null
}
class GeniusBar : SupportHandler {
    override var next: SupportHandler? = null
    override fun handle(request: SupportRequest) = if (request.severity <= 3) "Genius Bar resolved ${request.topic}" else null
}
class EngineeringSupport : SupportHandler {
    override var next: SupportHandler? = null
    override fun handle(request: SupportRequest) = "Engineering reviewed ${request.topic}"
}
