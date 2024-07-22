package entity.money_stock

import entity.Result
import entity.money.Money
import entity.counter.MoneyCounter
import entity.money_stock.public_interface.ICoinStock
import entity.vending_machine.public_interface.IVendingMachine

class CoinStock(
    private val moneyList: List<MoneyCounter>
) : ICoinStock {
    companion object {
        private const val NOT_ENOUGH_CHARGE = "釣銭不足です"

        val MONEY_LIST = listOf(
            Money.OneThousand,
            Money.FiveHundred,
            Money.OneHundred,
            Money.Fifty,
            Money.Ten
        )

        fun getInitialStock(): List<MoneyCounter> {
            return listOf(
                MoneyCounter(Money.Ten, 3),
                MoneyCounter(Money.Fifty, 1),
                MoneyCounter(Money.OneHundred, 3),
                MoneyCounter(Money.FiveHundred, 1),
                MoneyCounter(Money.OneThousand, 1)
            )
        }
    }

    override fun hasEnoughChange(vendingMachine: IVendingMachine): Result<Boolean> {
        val totalDeposit = vendingMachine.getTotalDeposit()
        val lowestProductPrice = vendingMachine.getLowestPrice()
        val charge = totalDeposit - lowestProductPrice
        if (charge < 0) return Result(true)

        return if (hasEnoughCharge(charge, vendingMachine)) {
            Result(true)
        } else {
            Result(false, NOT_ENOUGH_CHARGE)
        }
    }


    private fun hasEnoughCharge(
        charge: Int,
        vendingMachine: IVendingMachine
    ): Boolean {
        var target = charge
        run loop@ {
            MONEY_LIST.forEach { money ->
                if (target <= 0) return@loop

                val requiredAmount = target / money.value
                val totalStock = getAmount(money) + vendingMachine.getDepositAmount(money)
                val result = requiredAmount - totalStock
                if (result <= 0) {
                    // stock あり
                    target %= money.value
                    return@forEach
                }

                // stock 足らない
                target -= money.value * totalStock
            }
        }

        return target <= 0
    }

    private fun getAmount(money: Money): Int {
        val counter = moneyList.find {
            moneyCounter -> moneyCounter.isSame(money)
        }

        return counter?.amount ?: 0
    }

    private fun getTotalCharge(): Int {
        var result = 0
        moneyList.forEach { counter ->
            result += counter.getTotal()
        }

        return result
    }
}