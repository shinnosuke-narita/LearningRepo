package controller.menu.intent

sealed class MenuIntent {
    data class Error(val message: String) : MenuIntent()

    sealed class Transition : MenuIntent() {
        data object Wallet : Transition()
        data object PRODUCT : Transition()
        data object BUY : Transition()
        data object WORK : Transition()
    }
}