package controller.buy.processor

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import view.buy.BuySceneState

class ResultProcessor {
    fun handle(actionResult: BuyActionResult, currentState: MutableStateFlow<BuySceneState>) {
        when(actionResult) {
            is BuyActionResult.Deposit -> {
                currentState.update { it.copy(totalDeposit = actionResult.total) }
            }
            is BuyActionResult.Error -> {
                currentState.update { it.copy(errorMessage = actionResult.message) }
            }
            BuyActionResult.Finish -> {
                currentState.update { it.copy(isFinish = true) }
            }
        }
    }
}