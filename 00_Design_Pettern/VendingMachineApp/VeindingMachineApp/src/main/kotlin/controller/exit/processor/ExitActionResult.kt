package controller.exit.processor

sealed class ExitActionResult {
    data object Finish : ExitActionResult()
    data class Error(val message: String) : ExitActionResult()
}