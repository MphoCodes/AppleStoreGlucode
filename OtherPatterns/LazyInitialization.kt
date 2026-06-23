package com.mphocodes.androidpatterns.other

class ProductImageCache {
    val cache: MutableMap<String, ByteArray> by lazy { mutableMapOf() }
}
