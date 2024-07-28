package view.exit

import controller.base.IController
import controller.base.SceneState
import kotlinx.coroutines.flow.*
import view.exit.public_interface.IExitRouter

data class ExitSceneState(
    override val isFinish: Boolean = false,
    override val errorMessage: String? = null
) : SceneState

class ExitController(
    private val router: IExitRouter,
) : IController<ExitSceneState> {
    companion object {
        private const val EXIT = "a"
        private const val MENU = "b"
    }

    override val sceneState: SharedFlow<ExitSceneState> = MutableStateFlow(ExitSceneState()).asSharedFlow()

    override suspend fun nextAction(input: String) {
        when(input) {
//            EXIT -> { scene.showExitMessage() }
            MENU -> { router.pushMenuScene() }
            else -> {}
        }
    }
}