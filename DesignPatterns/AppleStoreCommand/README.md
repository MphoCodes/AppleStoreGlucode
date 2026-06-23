# Command

## Intent

Encapsulate an action as an object so the caller does not need to know how that action is performed.

## Pattern Overview

- Command decouples the sender of a request from the receiver that performs the work.
- Invokers execute commands.
- Commands call a receiver.
- The structure is: invoker -> command -> receiver.

## Problem Statement

Apple Store product pages often include image carousels. Users may navigate the carousel through several input modes:

- tapping left and right buttons,
- pressing keyboard arrows,
- selecting a thumbnail,
- or letting a timer advance the carousel.

Putting all interaction modes directly into one carousel class makes it harder to add or remove inputs. The Command pattern keeps each carousel action as a reusable command that any invoker can execute.

## Android/Kotlin Use Cases

- Mapping UI gestures, keyboard events, and scheduled events to the same ViewModel operation.
- Queueing cart actions for retry.
- Supporting undoable or replayable interactions.
- Keeping UI input handlers thin.

## Kotlin Example

```kotlin
package com.mphocodes.androidpatterns.command

class CarouselViewModel(private val imagePaths: List<String>) {
    var currentIndex: Int = 0
        private set

    fun navigateToNextItem() {
        currentIndex = (currentIndex + 1) % imagePaths.size
    }

    fun navigateToPreviousItem() {
        currentIndex = (currentIndex - 1 + imagePaths.size) % imagePaths.size
    }

    fun navigateToItem(index: Int) {
        if (index in imagePaths.indices) currentIndex = index
    }
}

fun interface CarouselCommand {
    fun execute()
}

class NavigateToNextItemCommand(
    private val receiver: CarouselViewModel
) : CarouselCommand {
    override fun execute() = receiver.navigateToNextItem()
}

class NavigateToPreviousItemCommand(
    private val receiver: CarouselViewModel
) : CarouselCommand {
    override fun execute() = receiver.navigateToPreviousItem()
}

class NavigateToItemCommand(
    private val receiver: CarouselViewModel,
    private val index: Int
) : CarouselCommand {
    override fun execute() = receiver.navigateToItem(index)
}

class TapInvoker(private val receiver: CarouselViewModel) {
    fun rightArrowButtonTapped() = NavigateToNextItemCommand(receiver).execute()
    fun leftArrowButtonTapped() = NavigateToPreviousItemCommand(receiver).execute()
    fun thumbnailTapped(index: Int) = NavigateToItemCommand(receiver, index).execute()
}

class KeyboardInvoker(private val receiver: CarouselViewModel) {
    fun rightArrowKeyPressed() = NavigateToNextItemCommand(receiver).execute()
    fun leftArrowKeyPressed() = NavigateToPreviousItemCommand(receiver).execute()
}

class TimerInvoker(private val receiver: CarouselViewModel) {
    fun tick() = NavigateToNextItemCommand(receiver).execute()
}
```

## Example Usage

```kotlin
val viewModel = CarouselViewModel(
    imagePaths = listOf("front.png", "side.png", "back.png")
)

val tapInvoker = TapInvoker(viewModel)
val keyboardInvoker = KeyboardInvoker(viewModel)
val timerInvoker = TimerInvoker(viewModel)

tapInvoker.rightArrowButtonTapped()
keyboardInvoker.leftArrowKeyPressed()
tapInvoker.thumbnailTapped(index = 2)
timerInvoker.tick()

println(viewModel.currentIndex)
```

## What To Notice

- `CarouselViewModel` is the receiver.
- Each command maps one action to the receiver.
- Tap, keyboard, and timer invokers can reuse the same commands.
- Adding voice navigation would mean adding another invoker, not changing the receiver.

## Practice Prompt

Apply this pattern to cart actions such as add item, remove item, apply promo code, and retry checkout.
