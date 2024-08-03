package controller.home.processor

import view.home.HomeSceneState

class HomeSceneStateDispatcher {
    fun handle(actionResult: HomeActionResult, currentState: HomeSceneState): HomeSceneState =
        when(actionResult) {
            HomeActionResult.Finish -> currentState.copy(isFinish = true)
            is HomeActionResult.Error -> currentState.copy(errorMessage = actionResult.message)
        }
}