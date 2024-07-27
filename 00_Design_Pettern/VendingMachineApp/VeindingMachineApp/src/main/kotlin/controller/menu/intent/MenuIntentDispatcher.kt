package controller.menu.intent

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MenuIntentDispatcher {
    fun handle(input: String): Flow<MenuIntent> =
        flow {
            when(input) {
                WALLET -> MenuIntent.Transition.Wallet
                PRODUCT -> MenuIntent.Transition.PRODUCT
                BUY -> MenuIntent.Transition.BUY
                WORK -> MenuIntent.Transition.WORK
                else -> MenuIntent.Error(ERROR_INPUT)
            }.also {
                emit(it)
            }
        }

    companion object {
        private const val WALLET = "a"
        private const val PRODUCT = "b"
        private const val BUY = "c"
        private const val WORK = "d"
        private const val ERROR_INPUT = "無効な入力値です"
    }
}