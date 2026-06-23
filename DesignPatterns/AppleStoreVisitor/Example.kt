package com.mphocodes.androidpatterns.visitor

interface StoreItem { fun accept(visitor: StoreItemVisitor): String }
class Phone(private val name: String) : StoreItem { override fun accept(visitor: StoreItemVisitor) = visitor.visitPhone(name) }
class ServicePlan(private val name: String) : StoreItem { override fun accept(visitor: StoreItemVisitor) = visitor.visitServicePlan(name) }
interface StoreItemVisitor { fun visitPhone(name: String): String; fun visitServicePlan(name: String): String }
class AccessibilityLabelVisitor : StoreItemVisitor {
    override fun visitPhone(name: String) = "Phone product: $name"
    override fun visitServicePlan(name: String) = "Service plan: $name"
}
