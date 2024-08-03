package controller.exit.intent

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ExitIntentDispatcher {
    fun handle(input: String): Flow<ExitIntent> =
        flow {
            when(input) {
                EXIT -> ExitIntent.Finish
                MENU -> ExitIntent.Transition.Menu
                else -> ExitIntent.Error("無効な入力値です")
            }.also {
                emit(it)
            }
        }

    companion object {
        private const val EXIT = "a"
        private const val MENU = "b"
    }
}