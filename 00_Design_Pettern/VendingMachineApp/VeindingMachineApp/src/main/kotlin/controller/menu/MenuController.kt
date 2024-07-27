package controller.menu

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import controller.base.IController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import view.menu.MenuSceneState
import view.menu.public_interface.IMenuRouter

class MenuController(private val router: IMenuRouter) : IController<MenuSceneState> {
    private val _sceneState = MutableStateFlow(MenuSceneState(false))
    override val sceneState: StateFlow<MenuSceneState> = _sceneState.asStateFlow()

    override suspend fun nextAction(input: String) {
        router.pushBuyScene()
        _sceneState.update { it.copy(isFinish = true) }
    }
}