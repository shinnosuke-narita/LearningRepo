package controller.home.intent

sealed class HomeIntent {
    data class Error(val message: String) : HomeIntent()

    sealed class Transition : HomeIntent() {
        data object BUY : Transition()
        data object WORK : Transition()
        data object EXIT : Transition()
    }
}