package view.buy

import model.money.Money
import controller.base.SceneState

data class BuySceneState(
    val totalDeposit: Int,
    val walletData: Map<Money, Int>,
    val errorMessage: String? = null,
    override val isFinish: Boolean = false
) : SceneState