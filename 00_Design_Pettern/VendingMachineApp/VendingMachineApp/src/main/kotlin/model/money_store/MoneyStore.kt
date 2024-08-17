package model.money_store

import model.money.Money
import model.money_store.public_interface.IMoneyStore

class MoneyStore(private val moneyMap: MutableMap<Money, Int>) : IMoneyStore {
    override fun getAmount(money: Money): Int = moneyMap[money] ?: 0

    override fun add(money: Money) {
        if (!money.isValid) return

        moneyMap[money] = moneyMap[money]?.inc() ?: 0
    }

    override fun sub(money: Money) {
        if (!money.isValid) return

        moneyMap[money]?.dec()?.let { decrementedAmount ->
            if (decrementedAmount < 0) return@let

            moneyMap[money] = decrementedAmount
        }
    }

    companion object {
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