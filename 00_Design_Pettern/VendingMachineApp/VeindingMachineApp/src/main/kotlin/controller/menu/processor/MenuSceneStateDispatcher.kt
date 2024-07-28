package controller.menu.processor

import kotlinx.coroutines.flow.update
import view.menu.MenuSceneState

class MenuSceneStateDispatcher {
    fun handle(actionResult: MenuActionResult, currentState: MenuSceneState): MenuSceneState =
        when(actionResult) {
            MenuActionResult.Finish -> currentState.copy(isFinish = true)
            is MenuActionResult.Error -> currentState.copy(errorMessage = actionResult.message)
        }
}