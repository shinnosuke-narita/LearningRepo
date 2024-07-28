package controller.menu

import controller.base.IController
import controller.menu.intent.MenuIntent
import controller.menu.intent.MenuIntentDispatcher
import controller.menu.processor.MenuSceneStateDispatcher
import controller.menu.processor.MenuActionResult
import kotlinx.coroutines.flow.*
import view.menu.MenuSceneState
import view.menu.public_interface.IMenuRouter

class MenuController(
    private val router: IMenuRouter,
    private val intentDispatcher: MenuIntentDispatcher = MenuIntentDispatcher(),
    private val sceneStateDispatcher:  MenuSceneStateDispatcher = MenuSceneStateDispatcher()
) : IController<MenuSceneState> {
    private val _sceneState = MutableStateFlow(MenuSceneState(false))
    override val sceneState: StateFlow<MenuSceneState> = _sceneState.asStateFlow()

    override suspend fun nextAction(input: String) {
        intentDispatcher
            .handle(input)
            .map { intent -> handleIntent(intent) }
            .map { actionResult -> sceneStateDispatcher.handle(actionResult, _sceneState.value) }
            .collect { state -> _sceneState.update { state } }
    }

    private fun handleIntent(intent: MenuIntent): MenuActionResult =
        when(intent) {
            is MenuIntent.Error -> TODO()
            MenuIntent.Transition.BUY -> actionTransitionBuy()
            MenuIntent.Transition.PRODUCT -> TODO()
            MenuIntent.Transition.WORK -> TODO()
            MenuIntent.Transition.Wallet -> actionTransitionWallet()
            MenuIntent.Transition.EXIT -> actionTransitionFinish()
        }

    private fun actionTransitionBuy(): MenuActionResult {
        router.pushBuyScene()
        return MenuActionResult.Finish
    }

    private fun actionTransitionWallet(): MenuActionResult {
        router.pushWalletScene()
        return MenuActionResult.Finish
    }

    private fun actionTransitionFinish(): MenuActionResult {
        router.pushFinishScene()
        return MenuActionResult.Finish
    }
}