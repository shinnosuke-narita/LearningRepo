package controller.buy.processor

import view.buy.BuySceneState

class BuyStateDispatcher {
    fun handle(
        actionResult: BuyActionResult,
        currentState: BuySceneState
    ): BuySceneState =
        when(actionResult) {
            is BuyActionResult.Deposit -> {
                 currentState.copy(totalDeposit = actionResult.total, errorMessage = null)
            }
            is BuyActionResult.Error -> {
                 currentState.copy(errorMessage = actionResult.message)
            }
            BuyActionResult.Finish -> {
                 currentState.copy(isFinish = true)
            }
        }
}