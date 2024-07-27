package di

import model.money.Money
import model.wallet.Wallet
import model.customer.Customer
import model.customer.public_interface.ICustomer

object PersonProvider {
    private var instance: Customer? = null

    private fun setUpPerson(): Customer {
        instance = Customer(Wallet(getInitialWallet()))

        return instance!!
    }

    fun provide(): ICustomer {
        return instance ?: setUpPerson()
    }

    private fun getInitialWallet(): MutableMap<Money, Int> {
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