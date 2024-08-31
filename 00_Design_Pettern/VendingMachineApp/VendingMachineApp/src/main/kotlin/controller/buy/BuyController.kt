package controller.buy

import application.customer.IGetWalletInfoApplicationService
import application.vending_machine.deposit.IGetDepositApplicationService
import application.vending_machine.put_money.IPutMoneyApplicationService
import controller.base.IController
import controller.buy.intent.BuyIntent
import controller.buy.intent.BuyIntentDispatcher
import controller.buy.processor.BuyActionResult
import controller.buy.processor.BuyStateDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import view.buy.BuySceneState
import view.buy.public_interface.IBuyRouter

class BuyController(
    private val router: IBuyRouter,
    private val putMoneyApplicationService: IPutMoneyApplicationService,
    private val getDepositApplicationService: IGetDepositApplicationService,
    private val getWalletInfoApplicationService: IGetWalletInfoApplicationService,
    private val intentDispatcher: BuyIntentDispatcher = BuyIntentDispatcher(),
    private val stateDispatcher: BuyStateDispatcher = BuyStateDispatcher()
): IController<BuySceneState> {
    private var _sceneState =
        BuySceneState(
            totalDeposit = getDepositApplicationService.handle().value,
            walletData = getWalletInfoApplicationService.handle()
        )
    override val sceneState = MutableSharedFlow<BuySceneState>(extraBufferCapacity = 1)

    override suspend fun nextAction(input: String) {
        intentDispatcher
            .handle(input)
            .map { intent -> handleIntent(intent) }
            .map { actionResult -> stateDispatcher.handle(actionResult, _sceneState) }
            .collect { state ->
                _sceneState = state
                sceneState.emit(state)
            }
    }

    override suspend fun loadCurrentState() {
        sceneState.emit(_sceneState)
    }

    private fun handleIntent(intent: BuyIntent): BuyActionResult =
        when(intent) {
            is BuyIntent.Deposit -> {
                depositAction(intent)
            }
            is BuyIntent.Error -> {
                BuyActionResult.Error(intent.message)
            }
            BuyIntent.Transition.Menu -> {
                router.pushMenu()
                BuyActionResult.Finish
            }
        }

    private fun depositAction(intent: BuyIntent.Deposit): BuyActionResult =
        putMoneyApplicationService.handle(intent.deposit).let {
            if (it.isError()) {
                BuyActionResult.Error(it.errorMessage)
            } else {
                BuyActionResult.Deposit(
                    intent.deposit,
                    it.data!!.value,
                    getWalletInfoApplicationService.handle()
                )
            }
        }
}

