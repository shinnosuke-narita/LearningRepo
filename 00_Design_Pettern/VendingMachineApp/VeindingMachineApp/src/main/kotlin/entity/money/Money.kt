package entity.money

sealed class Money(
    val value: Int,
    val isValid: Boolean,
) {
    companion object {
        const val COIN = 0
        const val BILL = 1
        const val UNIT = "円"
    }

    abstract fun getType(): Int
    abstract fun getSuffix(): String

    operator fun compareTo(other: Money): Int {
        return when {
            this.value > other.value -> 1
            this.value < other.value -> -1
            else -> 0
        }
    }

    abstract class Coin(value: Int, isValid: Boolean): Money(value, isValid) {
        override fun getType(): Int = COIN
        override fun getSuffix(): String = "${UNIT}玉"
    }

    abstract class Bill(value: Int, isValid: Boolean): Money(value, isValid) {
        override fun getType(): Int = BILL
        override fun getSuffix(): String = "${UNIT}札"
    }

    data object One          : Coin(1, false)
    data object Five         : Coin(5, false)
    data object Ten          : Coin(10, true)
    data object Fifty        : Coin(50, true)
    data object OneHundred   : Coin(100, true)
    data object FiveHundred  : Coin(500, true)
    data object OneThousand  : Bill(1000, true)
    data object FiveThousand : Bill(5000, false)
    data object TenThousand  : Bill(10000, false)
}


