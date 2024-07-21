package entity.customer

import entity.Result
import entity.money.Money
import entity.wallet.Wallet
import entity.customer.public_interface.ICustomer
import entity.vending_machine.public_interface.IVendingMachine

class Customer(private val wallet: Wallet) : ICustomer {
    fun collectMoneyData(showFunc: (String, Int) -> Unit) {
        wallet.collectMoneyInfo(showFunc)
    }

    fun getTotal(): Int {
        return wallet.getTotal()
    }

    override fun putMoney(money: Money, vendingMachine: IVendingMachine): Result<Int?> {
        val result = wallet.getMoney(money)
        if (result.isError()) {
            return Result(null, result.errorMessage)
        }

        return vendingMachine.insertMoney(money, this)
    }

    override fun returnMoney(money: Money) {
        wallet.addMoney(money)
    }
}