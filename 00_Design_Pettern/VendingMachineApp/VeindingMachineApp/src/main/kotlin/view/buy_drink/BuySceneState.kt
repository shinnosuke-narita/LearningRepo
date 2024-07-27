package view.buy_drink

import core.money.Money

data class BuySceneState(
    val totalDeposit: Int,
    val walletData: Map<Money, Int>,
    val errorMessage: String? = null,
    val isFinish: Boolean = false
)