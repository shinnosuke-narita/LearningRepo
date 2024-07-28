package controller.exit.processor

import controller.exit.ExitSceneState

class ExitSceneStateDispatcher {
    fun handle(
        actionResult: ExitActionResult,
        currentState: ExitSceneState
    ): ExitSceneState =
        when(actionResult) {
            ExitActionResult.Finish -> currentState.copy(isFinish = true)
            is ExitActionResult.Error -> currentState.copy(errorMessage = actionResult.message)
        }
}