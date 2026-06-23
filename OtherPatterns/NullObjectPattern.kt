package com.mphocodes.androidpatterns.other

interface PromotionBanner { fun message(): String }
object NoPromotionBanner : PromotionBanner { override fun message() = "" }
class TradeInPromotionBanner : PromotionBanner { override fun message() = "Trade in and save" }
