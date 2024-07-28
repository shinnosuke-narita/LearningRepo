package controller.menu.processor

sealed class MenuActionResult {
    data object Finish : MenuActionResult()
    data class Error(val message: String) : MenuActionResult()
}