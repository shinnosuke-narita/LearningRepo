package entity.wallet

import entity.Result
import entity.customer.public_interface.IWallet
import entity.money.Money
import entity.counter.MoneyCounter

class Wallet(private val moneyList: List<MoneyCounter>) : IWallet {
    companion object {
        private const val NO_MONEY_MESSAGE = "そのお金はありません"
    }

    fun getTotal(): Int {
        var result = 0
        moneyList.forEach{ counter ->
            result += counter.getTotal()
        }

        return result
    }

    fun collectMoneyInfo(show: (String, Int) -> Unit) {
        moneyList.forEach{ counter ->
            show(counter.getMoneyName(), counter.amount)
        }
    }

    private fun isOneAndMore(money: Money): Boolean {
        val counter = moneyList.find { counter -> counter.isSame(money) }
            ?: return false

        return counter.amount > 0
    }

    override fun getMoney(money: Money): Result<Money?> {
        if (!isOneAndMore(money)) {
            return Result(null, NO_MONEY_MESSAGE)
        }

        moneyList.forEach { moneyCounter ->
            moneyCounter.subtract(money)
        }

        return Result(money)
    }

    override fun addMoney(money: Money) {
        moneyList.forEach { counter ->
            if (!counter.isSame(money)) return@forEach

            counter.add(money)
        }
    }
}