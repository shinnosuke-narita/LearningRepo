package controller.buy

import model.customer.public_interface.ICustomer
import model.vending_machine.public_interface.IVendingMachine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import controller.base.IController
import controller.buy.intent.BuyIntent
import controller.buy.intent.BuyIntentDispatcher
import controller.buy.processor.BuyActionResult
import controller.buy.processor.BuyActionProcessor
import view.buy.BuySceneState
import view.buy.public_interface.IBuyRouter

class BuyController(
    private val router: IBuyRouter,
    private val customer: ICustomer,
    private val vendingMachine: IVendingMachine,
): IController<BuySceneState> {
    private val _sceneState =
        MutableStateFlow(
            BuySceneState(
                totalDeposit = vendingMachine.getTotalDeposit(),
                walletData = customer.getWalletInfo()
            )
        )
    override val sceneState = _sceneState.asStateFlow()

    override suspend fun nextAction(input: String) {
        BuyIntentDispatcher()
            .handle(input)
            .map { intent -> handleIntent(intent) }
            .collect { result -> BuyActionProcessor().handle(result, _sceneState) }
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

