package com.mphocodes.androidpatterns.state

interface OrderState { fun label(): String; fun next(): OrderState }
class DraftOrder : OrderState { override fun label() = "Draft"; override fun next() = PaidOrder() }
class PaidOrder : OrderState { override fun label() = "Paid"; override fun next() = ShippedOrder() }
class ShippedOrder : OrderState { override fun label() = "Shipped"; override fun next() = this }
class Order { private var state: OrderState = DraftOrder(); fun advance() { state = state.next() }; fun status() = state.label() }
