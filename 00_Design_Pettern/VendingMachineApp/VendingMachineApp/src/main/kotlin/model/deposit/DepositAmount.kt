package model.deposit

data class DepositAmount(val value: Int) {
    init {
        if (value < 0) throw IllegalArgumentException(ERROR_MESSAGE)
    }

    operator fun plus(other: DepositAmount): DepositAmount = DepositAmount(value + other.value)
    operator fun minus(other: DepositAmount): DepositAmount = DepositAmount(value - other.value)

    companion object {
        private const val ERROR_MESSAGE = "引数がマイナスです"
    }
}