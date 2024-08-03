package controller.exit.intent

sealed class ExitIntent {
    data class Error(val message: String) : ExitIntent()
    data object Finish : ExitIntent()
    sealed class Transition : ExitIntent() {
        data object Menu : Transition()
    }
}