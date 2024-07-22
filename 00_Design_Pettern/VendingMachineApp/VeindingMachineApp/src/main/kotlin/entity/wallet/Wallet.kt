package entity.wallet

import entity.Result
import entity.customer.public_interface.IWallet
import entity.money.Money

class Wallet(private val moneyMap: MutableMap<Money, Int>) : IWallet {
    companion object {
        private const val NO_MONEY_MESSAGE = "そのお金はありません"
    }

    override fun getTotal(): Int =
        moneyMap
            .map { (money, amount) ->
                money.value * amount
            }
            .sum()

    fun collectMoneyInfo(show: (String, Int) -> Unit) {
        moneyMap.forEach{ (money, amount) ->
            show(money.name, amount)
        }
    }

    private fun isOneAndMore(money: Money): Boolean = moneyMap.getOrDefault(money, 0) >= 1

    override fun spendMoney(money: Money): Result<Unit> {
        if (!isOneAndMore(money)) {
            return Result(Unit, NO_MONEY_MESSAGE)
        }

        moneyMap[money] = moneyMap[money]!!.dec()

        return Result(Unit)
    }

    override fun putMoney(money: Money) {
        moneyMap.getOrDefault(money, 0).let { current ->
            moneyMap[money] = current + 1
        }
    }
}