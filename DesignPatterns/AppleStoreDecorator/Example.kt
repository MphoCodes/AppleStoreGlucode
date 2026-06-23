package com.mphocodes.androidpatterns.decorator

interface OrderLine { fun description(): String; fun total(): Double }
class ProductLine(private val name: String, private val price: Double) : OrderLine {
    override fun description() = name
    override fun total() = price
}
class AppleCareDecorator(private val line: OrderLine) : OrderLine {
    override fun description() = "${line.description()} + AppleCare"
    override fun total() = line.total() + 199.0
}
class GiftWrapDecorator(private val line: OrderLine) : OrderLine {
    override fun description() = "${line.description()} + gift wrap"
    override fun total() = line.total() + 9.99
}
