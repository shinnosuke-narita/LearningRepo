package model.money_store

import model.result.CoreResult
import model.money.Money
import model.money_store.public_interface.IMoneyStore

data class Deposit(val value: Int) {
    init {
        if (value < 0) throw IllegalArgumentException("引数の値がマイナスです。")
    }

    operator fun plus(other: Deposit): Deposit = Deposit(value + other.value)
    operator fun minus(other: Deposit): Deposit = Deposit(value - other.value)
}

class MoneyStore(
    private val moneyMap: MutableMap<Money, Int>,
    private var _deposit: Deposit = Deposit(0)
) : IMoneyStore {
    override val deposit: Deposit get() = _deposit

    override fun putMoney(money: Money, lowestProductPrice: Int): CoreResult<Deposit?> {
        if (!money.isValid) {
            val error = INVALID_MESSAGE.format(money.name)
            return CoreResult(null, error)
        }

        addMoney(money)

        val change = _deposit.value - lowestProductPrice
        if (!hasEnoughChange(change)) {
            rollback(money)
            return CoreResult(null, NOT_ENOUGH_CHARGE)
        }

        return CoreResult(_deposit)
    }

    private fun hasEnoughChange(change: Int): Boolean {
        var target = change
        run loop@ {
            MONEY_LIST.forEach { money ->
                if (target <= 0) return@loop

                val requiredAmount = target / money.value
                val currentAmount = getAmount(money)
                val result = requiredAmount - currentAmount
                if (result <= 0) {
                    // stock あり
                    target %= money.value
                    return@forEach
                }

                // stock 足らない
                target -= money.value * currentAmount
            }
        }

        return target <= 0
    }

    override fun getAmount(money: Money): Int = moneyMap[money] ?: 0

    private fun rollback(money: Money) {
        _deposit -= Deposit(money.value)
        subtract(money)
    }

    private fun addMoney(money: Money) {
        _deposit += Deposit(money.value)
        add(money)
    }

    private fun add(money: Money) {
        if (!money.isValid) return

        moneyMap[money] = moneyMap[money]?.inc() ?: 0
    }

    private fun subtract(money: Money) {
        if (!money.isValid) return

        moneyMap[money]?.dec()?.let { decrementedAmount ->
            if (decrementedAmount < 0) return@let

            moneyMap[money] = decrementedAmount
        }
    }

    companion object {
        private const val NOT_ENOUGH_CHARGE = "釣銭不足です。\n入金されたお金を返却いたしました。"
        private const val INVALID_MESSAGE = "%d%sはご利用いただけません"

        val MONEY_LIST =
            listOf(
                Money.OneThousand,
                Money.FiveHundred,
                Money.OneHundred,
                Money.Fifty,
                Money.Ten
            )

        fun getInitialStock(): MutableMap<Money, Int> =
            mutableMapOf(
                Money.Ten to 3,
                Money.Fifty to 1,
                Money.OneHundred to 3,
                Money.FiveHundred to 1,
                Money.OneThousand to 1
            )
    }
}