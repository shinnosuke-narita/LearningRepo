package view.buy.public_interface

import kotlinx.coroutines.flow.StateFlow
import view.buy.BuySceneState

interface IBuyController {
    val sceneState: StateFlow<BuySceneState>
    fun nextAction(input: String)
}