package controller.menu.processor

sealed class MenuActionResult {
    data object Finish : MenuActionResult()
}