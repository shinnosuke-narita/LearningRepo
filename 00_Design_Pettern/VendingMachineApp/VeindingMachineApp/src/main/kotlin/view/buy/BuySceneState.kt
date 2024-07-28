package view.buy

import model.money.Money
import controller.base.SceneState

data class BuySceneState(
    override val errorMessage: String? = null,
    override val isFinish: Boolean = false,
    val totalDeposit: Int,
    val walletData: Map<Money, Int>
) : SceneState