package model.money

import model.result.CoreResult
import java.lang.NumberFormatException

class MoneyFactory private constructor (val value: Money) {
    companion object {
        private const val BLANK_MESSAGE = "入力値がありません"
        private const val INVALID_MESSAGE = "無効な値です"
        private const val PREFIX_CHAR_NUM = 1


        fun create(input: String): CoreResult<Money?> {
            if (input.isBlank()) {
                return CoreResult(null, BLANK_MESSAGE)
            }

            return handleRawInput(input)
                ?.toMoney()
                ?.let { money ->
                    CoreResult(money)
                } ?: CoreResult(null, INVALID_MESSAGE)
        }

        private fun handleRawInput(input: String): Int? =
             try {
                input.drop(PREFIX_CHAR_NUM).toInt()
            } catch (e: NumberFormatException) {
                null
            }

        private fun Int.toMoney(): Money? = Money.from(this)
    }
}