# Template Method

## Intent

Define the fixed skeleton of an algorithm in a base class while allowing subclasses to override selected steps.

## Pattern Overview

- Template Method uses inheritance deliberately.
- The base class owns the algorithm order.
- Subclasses customize individual steps without changing that order.
- The template method is usually `final` so subclasses cannot accidentally break the sequence.

## Problem Statement

Apple Watch Studio configurations may be represented differently in the UI and backend. The app needs to map a watch configuration into a backend request in a consistent order:

1. map case size,
2. map case material,
3. map band,
4. map band size,
5. map engraving.

Different watch series may need different mapping logic, but the processing order should stay fixed. Template Method keeps the order in one place and lets each series override only the steps that differ.

## Android/Kotlin Use Cases

- Enforcing a fixed sequence for a multi-step mapper.
- Keeping request-building steps consistent across product variants.
- Implementing import/export flows where each type customizes only part of the algorithm.

## Kotlin Example

```kotlin
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
```

## Example Usage

```kotlin
val series10Request = Series10AppleWatchMapper().process()
val hermesRequest = HermesSeries10AppleWatchMapper().process()

println(series10Request.caseMaterial) // Titanium
println(hermesRequest.engraving) // null
```

## What To Notice

- `process()` is the template method because it defines the algorithm sequence.
- Subclasses override step methods such as `mapCaseSize()` and `mapBand()`.
- Shared defaults live in the base class.
- This pattern is a poor fit when composition would keep the design simpler.

## Practice Prompt

Apply this pattern to a checkout request builder where guest checkout, member checkout, and business checkout share the same request-building sequence but customize selected steps.
