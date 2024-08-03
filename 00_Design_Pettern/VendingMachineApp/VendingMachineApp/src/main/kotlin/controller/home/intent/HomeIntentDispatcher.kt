package controller.home.intent

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeIntentDispatcher {
    fun handle(input: String): Flow<HomeIntent> =
        flow {
            when(input) {
                BUY -> HomeIntent.Transition.BUY
                WORK -> HomeIntent.Transition.WORK
                EXIT -> HomeIntent.Transition.EXIT
                else -> HomeIntent.Error(ERROR_INPUT)
            }.also {
                emit(it)
            }
        }

    companion object {
        private const val BUY = "a"
        private const val WORK = "b"
        private const val EXIT = "c"
        private const val ERROR_INPUT = "無効な入力値です"
    }
}