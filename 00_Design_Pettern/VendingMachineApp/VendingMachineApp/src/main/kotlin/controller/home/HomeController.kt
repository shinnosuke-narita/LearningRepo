package controller.home

import application.vending_machine.product.IGetProductInfoApplicationService
import controller.base.IController
import controller.home.intent.HomeIntent
import controller.home.intent.HomeIntentDispatcher
import controller.home.processor.HomeActionResult
import controller.home.processor.HomeSceneStateDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import view.home.HomeSceneState
import view.home.public_interface.IHomeRouter

class HomeController(
    private val router: IHomeRouter,
    private val applicationService: IGetProductInfoApplicationService,
    private val intentDispatcher: HomeIntentDispatcher = HomeIntentDispatcher(),
    private val sceneStateDispatcher:  HomeSceneStateDispatcher = HomeSceneStateDispatcher()
) : IController<HomeSceneState> {
    private val _currentState = HomeSceneState(productInfo = applicationService.handle())
    override val sceneState = MutableSharedFlow<HomeSceneState>(extraBufferCapacity = 1)

    override suspend fun nextAction(input: String) {
        intentDispatcher
            .handle(input)
            .map { intent -> handleIntent(intent) }
            .map { actionResult -> sceneStateDispatcher.handle(actionResult, _currentState) }
            .collect { state -> sceneState.emit(state)}
    }

    override suspend fun loadCurrentState() {
        sceneState.emit(_currentState)
    }

    private fun handleIntent(intent: HomeIntent): HomeActionResult =
        when(intent) {
            is HomeIntent.Error -> HomeActionResult.Error(intent.message)
            HomeIntent.Transition.BUY -> actionTransitionBuy()
            HomeIntent.Transition.WORK -> TODO()
            HomeIntent.Transition.EXIT -> actionTransitionFinish()
        }

    private fun actionTransitionBuy(): HomeActionResult {
        router.pushBuyScene()
        return HomeActionResult.Finish
    }

    private fun actionTransitionFinish(): HomeActionResult {
        router.pushFinishScene()
        return HomeActionResult.Finish
    }
}