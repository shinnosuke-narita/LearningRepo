package view.buy_drink.intent

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

    fun handle(input: String): Flow<Intent> =
        flow {
            when {
                input == MENU_INPUT -> Intent.Transition.Menu
                input.startsWith(PRODUCT_PREFIX) -> {
                    Intent.Error("not implementation")
                }
                input.startsWith(DEPOSIT_PREFIX) -> {
                    MoneyFactory.create(input).let { result ->
                        if (result.isError()) {
                            Intent.Error(result.errorMessage)
                        } else {
                            Intent.Deposit(result.data!!)
                        }
                    }
                }
                input == CHARGE_INPUT -> {
                    Intent.Error("not implementation")
                }
                else -> {
                    Intent.Error("not implementation")
                }
            }.also {
                emit(it)
            }
        }
}