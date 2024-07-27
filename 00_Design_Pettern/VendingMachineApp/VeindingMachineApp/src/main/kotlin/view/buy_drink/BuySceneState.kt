package view.buy_drink

import core.money.Money
import view.base.scene.SceneState

data class BuySceneState(
    val totalDeposit: Int,
    val walletData: Map<Money, Int>,
    val errorMessage: String? = null,
    override val isFinish: Boolean = false
) : SceneState(isFinish)