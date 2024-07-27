package core.customer

import core.customer.public_interface.ICustomer
import core.money.Money
import core.vending_machine.public_interface.IVendingMachine
import core.wallet.Wallet
import core.result.CoreResult


class Customer(private val wallet: Wallet) : ICustomer {
    override fun putMoney(money: Money, vendingMachine: IVendingMachine): CoreResult<Int?> =
        wallet.spendMoney(money).let { result ->
            if (result.isError()) {
                CoreResult(null, result.errorMessage)
            } else {
                vendingMachine.insertMoney(money, this)
            }
        }

    override fun returnMoney(money: Money) {
        wallet.putMoney(money)
    }

    override fun getWalletInfo(): Map<Money, Int> = wallet.moneyInfo
}