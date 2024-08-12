package model.customer

import model.customer.public_interface.ICustomer
import model.money.Money
import model.vending_machine.public_interface.IVendingMachine
import model.wallet.Wallet
import model.result.CoreResult


class Customer(private val wallet: Wallet) : ICustomer {
    override fun putMoney(money: Money, vendingMachine: IVendingMachine): CoreResult<Int?> =
        wallet.spendMoney(money).let { result ->
            if (result.isError()) {
                CoreResult(null, result.errorMessage)
            } else {
                vendingMachine.onPutMoney(money, this)
            }
        }

    override fun receiveMoney(money: Money) {
        wallet.putMoney(money)
    }

    override fun getWalletInfo(): Map<Money, Int> = wallet.moneyInfo
}