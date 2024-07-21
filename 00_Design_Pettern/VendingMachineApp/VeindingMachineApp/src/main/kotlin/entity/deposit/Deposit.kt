package entity.deposit

import entity.Result
import entity.money.Money
import entity.counter.MoneyCounter
import entity.deposit.public_interface.IDeposit
import entity.vending_machine.public_interface.IVendingMachine

class Deposit(private val moneyList: List<MoneyCounter>) : IDeposit {
    companion object {
        private const val INITIAL_AMOUNT: Int = 0
        private const val INVALID_MESSAGE = "%d%sはご利用いただけません"

        fun getInitialDeposit(): List<MoneyCounter> {
            return listOf(
                MoneyCounter(Money.Ten, INITIAL_AMOUNT),
                MoneyCounter(Money.Fifty, INITIAL_AMOUNT),
                MoneyCounter(Money.OneHundred, INITIAL_AMOUNT),
                MoneyCounter(Money.FiveHundred, INITIAL_AMOUNT),
                MoneyCounter(Money.OneThousand, INITIAL_AMOUNT)
            )
        }
    }

    override fun getTotal(): Int {
        var total = 0
        moneyList.forEach { moneyCounter ->
            total += moneyCounter.getTotal()
        }

        return total
    }

    override fun insertMoney(money: Money, vendingMachine: IVendingMachine): Result<Int?> {
        if (!money.isValid) {
            val error = INVALID_MESSAGE.format(money.value, money.getSuffix())
            return Result(null, error)
        }

        add(money)

        vendingMachine.haveEnoughChange().onError { error ->
            subtract(money)
            return Result(null, error)
        }

        return Result(getTotal())
    }

    private fun add(money: Money) {
        moneyList.forEach { counter ->
            counter.add(money)
        }
    }

    private fun subtract(money: Money) {
        moneyList.forEach { counter ->
            counter.subtract(money)
        }
    }

    override fun getAmount(money: Money): Int {
        val counter = moneyList.find {
            moneyCounter -> moneyCounter.isSame(money)
        }

        return counter?.amount ?: 0
    }
}