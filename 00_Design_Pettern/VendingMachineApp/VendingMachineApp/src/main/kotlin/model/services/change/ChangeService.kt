package model.services.change

import model.money.Money
import model.money_store.MoneyStore

data class Change(val value: Int) {
    init {
        if (value < 0) throw IllegalArgumentException("引数がマイナスです")
    }
}

interface IChangeService {
    fun hasEnoughChange(change: Change): Boolean
}

class ChangeService(private val moneyStore: MoneyStore) : IChangeService {
    override fun hasEnoughChange(change: Change): Boolean {
        var target = change.value
        run loop@ {
            MONEY_LIST.forEach { money ->
                if (target <= 0) return@loop

                val requiredAmount = target / money.value
                val currentAmount = moneyStore.getAmount(money)
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

    companion object {
        val MONEY_LIST =
            listOf(
                Money.OneThousand,
                Money.FiveHundred,
                Money.OneHundred,
                Money.Fifty,
                Money.Ten
            )
    }
}