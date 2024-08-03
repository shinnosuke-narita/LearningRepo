package controller.buy

import controller.base.IController
import controller.buy.intent.BuyIntent
import controller.buy.intent.BuyIntentDispatcher
import controller.buy.processor.BuyActionResult
import controller.buy.processor.BuyStateDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import model.customer.public_interface.ICustomer
import model.vending_machine.public_interface.IVendingMachine
import view.buy.BuySceneState
import view.buy.public_interface.IBuyRouter

class BuyController(
    private val router: IBuyRouter,
    private val customer: ICustomer,
    private val vendingMachine: IVendingMachine,
    private val intentDispatcher: BuyIntentDispatcher = BuyIntentDispatcher(),
    private val stateDispatcher: BuyStateDispatcher = BuyStateDispatcher()
): IController<BuySceneState> {
    private var _sceneState =
        BuySceneState(
            totalDeposit = vendingMachine.getTotalDeposit(),
            walletData = customer.getWalletInfo()
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
        customer.putMoney(intent.deposit, vendingMachine).let {
            if (it.isError()) {
                BuyActionResult.Error(it.errorMessage)
            } else {
                BuyActionResult.Deposit(
                    intent.deposit,
                    it.data!!,
                    customer.getWalletInfo()
                )
            }
        }
}

