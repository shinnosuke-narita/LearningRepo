package model.wallet

import model.result.CoreResult
import model.customer.public_interface.IWallet
import model.money.Money


class Wallet(private val _moneyMap: MutableMap<Money, Int>) : IWallet {
    override val moneyInfo: Map<Money, Int> = _moneyMap

    override fun getTotal(): Int =
        _moneyMap
            .map { (money, amount) ->
                money.value * amount
            }
            .sum()

    private fun isOneAndMore(money: Money): Boolean = _moneyMap.getOrDefault(money, 0) >= 1

    override fun spendMoney(money: Money): CoreResult<Unit> {
        if (!isOneAndMore(money)) {
            return CoreResult(Unit, NO_MONEY_MESSAGE)
        }

        _moneyMap[money] = _moneyMap[money]!!.dec()

        return CoreResult(Unit)
    }

    override fun putMoney(money: Money) {
        _moneyMap.getOrDefault(money, 0).let { current ->
            _moneyMap[money] = current + 1
        }
    }

    companion object {
        private const val NO_MONEY_MESSAGE = "お財布の中にそのお金はありません"

        fun getInitialWallet(): MutableMap<Money, Int> {
            return mutableMapOf(
                Money.Ten to 10,
                Money.Fifty to 10,
                Money.OneHundred to 10,
                Money.FiveHundred to 10,
                Money.OneThousand to 10,
                Money.OneThousand to 10,
                Money.OneThousand to 10
            )
        }
    }

}