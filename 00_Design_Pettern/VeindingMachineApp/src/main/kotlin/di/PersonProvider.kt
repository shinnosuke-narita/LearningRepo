package di

import entity.money.Money
import entity.counter.MoneyCounter
import entity.wallet.Wallet
import entity.customer.Customer
import entity.customer.public_interface.ICustomer

object PersonProvider {
    private var instance: Customer? = null

    private fun setUpPerson(): Customer {
        instance = Customer(Wallet(getInitialWallet()))

        return instance!!
    }

    fun provide(): ICustomer {
        return instance ?: setUpPerson()
    }

    private fun getInitialWallet(): List<MoneyCounter> {
        return listOf(
            MoneyCounter(Money.Ten, 10),
            MoneyCounter(Money.Fifty, 10),
            MoneyCounter(Money.OneHundred, 10),
            MoneyCounter(Money.FiveHundred, 10),
            MoneyCounter(Money.OneThousand, 10),
            MoneyCounter(Money.OneThousand, 10),
            MoneyCounter(Money.OneThousand, 10)
        )
    }
}