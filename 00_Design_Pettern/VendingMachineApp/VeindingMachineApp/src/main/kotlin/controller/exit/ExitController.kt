package controller.exit

import controller.base.IController
import controller.base.SceneState
import controller.exit.intent.ExitIntent
import controller.exit.intent.ExitIntentDispatcher
import controller.exit.processor.ExitActionResult
import controller.exit.processor.ExitSceneStateDispatcher
import kotlinx.coroutines.flow.*
import view.exit.public_interface.IExitRouter

data class ExitSceneState(
    override val isFinish: Boolean = false,
    override val errorMessage: String? = null,
) : SceneState

class ExitController(
    private val router: IExitRouter,
    private val intentDispatcher: ExitIntentDispatcher = ExitIntentDispatcher(),
    private val stateDispatcher: ExitSceneStateDispatcher = ExitSceneStateDispatcher(),
) : IController<ExitSceneState> {
    private var _currentSate = ExitSceneState()
    override val sceneState =  MutableSharedFlow<ExitSceneState>(extraBufferCapacity = 1)

    override suspend fun nextAction(input: String) {
        intentDispatcher
            .handle(input)
            .map { intent -> handleIntent(intent) }
            .map { result -> stateDispatcher.handle(result, _currentSate) }
            .collect { state ->
                _currentSate = state
                sceneState.emit(_currentSate)
            }
    }

    override suspend fun loadCurrentState() {
        sceneState.emit(_currentSate)
    }

    private fun handleIntent(intent: ExitIntent): ExitActionResult =
        when(intent) {
            ExitIntent.Transition.Menu -> {
                router.pushMenuScene()
                ExitActionResult.Finish
            }
            is ExitIntent.Error -> ExitActionResult.Error(intent.message)
            ExitIntent.Finish -> ExitActionResult.Finish
        }

}