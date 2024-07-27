package view.buy_drink.public_interface

import kotlinx.coroutines.flow.StateFlow
import view.buy_drink.BuySceneState

interface IBuyController {
    val sceneState: StateFlow<BuySceneState>
    fun putMoney(input: String)
    fun nextAction(input: String)
}