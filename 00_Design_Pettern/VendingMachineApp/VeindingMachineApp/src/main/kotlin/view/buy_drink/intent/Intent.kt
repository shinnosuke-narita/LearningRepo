package view.buy_drink.intent

import core.money.Money

sealed class Intent {
    data class Deposit(val deposit: Money) : Intent()
    data class Error(val message: String) : Intent()

    sealed class Transition: Intent() {
        data object Menu : Transition()
    }
}