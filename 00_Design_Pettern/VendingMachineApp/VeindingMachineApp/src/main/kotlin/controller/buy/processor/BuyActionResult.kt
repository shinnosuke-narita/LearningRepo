package controller.buy.processor

import core.money.Money


sealed class BuyActionResult {
    data class Deposit(
        val deposit: Money,
        val total: Int,
        val walletData: Map<Money, Int>
    ) : BuyActionResult()
    data class Error(val message: String): BuyActionResult()
    data object Finish : BuyActionResult()
}