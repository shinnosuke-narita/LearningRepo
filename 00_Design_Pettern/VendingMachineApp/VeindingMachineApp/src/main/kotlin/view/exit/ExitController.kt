package view.exit

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import view.base.IController
import view.base.SceneState
import view.exit.public_interface.IExitRouter

data class ExitSceneState(override val isFinish: Boolean = false) : SceneState

class ExitController(
    private val router: IExitRouter,
) : IController<ExitSceneState> {
    companion object {
        private const val EXIT = "a"
        private const val MENU = "b"
    }

    override val sceneState: StateFlow<ExitSceneState> = MutableStateFlow(ExitSceneState()).asStateFlow()

    override fun nextAction(input: String) {
        when(input) {
//            EXIT -> { scene.showExitMessage() }
            MENU -> { router.pushMenuScene() }
            else -> {}
        }
    }
}