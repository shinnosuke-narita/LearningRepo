package controller.home.processor

sealed class HomeActionResult {
    data object Finish : HomeActionResult()
    data class Error(val message: String) : HomeActionResult()
}