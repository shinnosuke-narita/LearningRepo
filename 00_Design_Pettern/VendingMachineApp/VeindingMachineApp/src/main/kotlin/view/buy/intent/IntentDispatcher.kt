package view.buy.intent

import core.money.MoneyFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IntentDispatcher {
    companion object {
        private const val PRODUCT_PREFIX = "a"
        private const val DEPOSIT_PREFIX = "b"
        private const val CHARGE_INPUT = "c"
        private const val MENU_INPUT = "m"
    }

    fun handle(input: String): Flow<BuyIntent> =
        flow {
            when {
                input == MENU_INPUT -> BuyIntent.Transition.Menu
                input.startsWith(PRODUCT_PREFIX) -> {
                    BuyIntent.Error("not implementation")
                }
                input.startsWith(DEPOSIT_PREFIX) -> {
                    MoneyFactory.create(input).let { result ->
                        if (result.isError()) {
                            BuyIntent.Error(result.errorMessage)
                        } else {
                            BuyIntent.Deposit(result.data!!)
                        }
                    }
                }
                input == CHARGE_INPUT -> {
                    BuyIntent.Error("not implementation")
                }
                else -> {
                    BuyIntent.Error("not implementation")
                }
            }.also {
                emit(it)
            }
        }
}