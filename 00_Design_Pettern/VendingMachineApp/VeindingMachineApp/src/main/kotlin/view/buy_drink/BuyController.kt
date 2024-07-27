package view.buy_drink

import core.customer.public_interface.ICustomer
import core.vending_machine.public_interface.IVendingMachine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import view.base.IController
import view.buy_drink.intent.BuyIntent
import view.buy_drink.intent.IntentDispatcher
import view.buy_drink.processor.BuyActionResult
import view.buy_drink.processor.ResultProcessor
import view.buy_drink.public_interface.IBuyRouter

class BuyController(
    private val router: IBuyRouter,
    private val customer: ICustomer,
    private val vendingMachine: IVendingMachine,
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
): IController<BuySceneState> {
    private val _sceneState =
        MutableStateFlow(
            BuySceneState(
                totalDeposit = vendingMachine.getTotalDeposit(),
                walletData = customer.getWalletInfo()
            )
        )
    override val sceneState = _sceneState.asStateFlow()

    override fun nextAction(input: String) {
        coroutineScope.launch {
            IntentDispatcher()
                .handle(input)
                .map { intent -> handleIntent(intent) }
                .collect { result -> ResultProcessor().handle(result, _sceneState) }
        }
    }

    private fun handleIntent(intent: BuyIntent): BuyActionResult =
        when(intent) {
            is BuyIntent.Deposit -> {
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
            is BuyIntent.Error -> {
                BuyActionResult.Error(intent.message)
            }
            BuyIntent.Transition.Menu -> {
                router.pushMenu()
                BuyActionResult.Finish
            }
        }
}

