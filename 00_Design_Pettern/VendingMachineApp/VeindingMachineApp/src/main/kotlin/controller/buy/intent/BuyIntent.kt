package controller.buy.intent

import core.money.Money

sealed class BuyIntent {
    data class Deposit(val deposit: Money) : BuyIntent()
    data class Error(val message: String) : BuyIntent()

    sealed class Transition: BuyIntent() {
        data object Menu : Transition()
    }
}