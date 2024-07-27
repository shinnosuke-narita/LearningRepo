package view.menu

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import view.base.IController
import view.base.SceneState
import view.menu.public_interface.IMenuRouter

data class MenuSceneState(override val isFinish: Boolean) : SceneState(false)

class MenuController(private val router: IMenuRouter) : IController<MenuSceneState> {
    private val _sceneState = MutableStateFlow(MenuSceneState(false))
    override val sceneState: StateFlow<MenuSceneState> = _sceneState.asStateFlow()

    override fun nextAction(input: String) {
        when(input) {
            SHOW_WALLET -> { router.pushWalletScene() }
            BUY_DRINK -> { router.pushBuyScene() }
            else -> { router.pushFinishScene() }
        }

        _sceneState.update { it.copy(isFinish = true) }
    }

    companion object {
        private const val SHOW_WALLET = "a"
        private const val SHOW_PRODUCT = "b"
        private const val BUY_DRINK = "c"
        private const val WORK = "d"
    }
}