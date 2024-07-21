package domain.buy.model

import entity.Result
import java.lang.NumberFormatException

class InputMoney private constructor (val value: Int) {
    companion object {
        private const val BLANK_MESSAGE = "入力値がありません"
        private const val INVALID_MESSAGE = "無効な値です"


        fun create(input: String): Result<InputMoney?> {
            if (input.isBlank()) {
                return Result(null, BLANK_MESSAGE)
            }

            val value = try {
                input.toInt()
            } catch (e: NumberFormatException) {
                return Result(null, INVALID_MESSAGE)
            }

            return Result(InputMoney(value))
        }
    }
}