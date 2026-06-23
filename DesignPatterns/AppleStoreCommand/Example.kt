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
