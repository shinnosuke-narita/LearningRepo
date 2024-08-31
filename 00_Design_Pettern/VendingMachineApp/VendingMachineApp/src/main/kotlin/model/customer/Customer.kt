package model.customer

import model.customer.public_interface.ICustomer
import model.customer.public_interface.IWallet
import model.money.Money
import model.wallet.Wallet
import model.result.CoreResult

class Customer(private val wallet: IWallet) : ICustomer {
    override fun receiveMoney(money: Money) {
        wallet.putMoney(money)
    }

    override fun getWalletInfo(): Map<Money, Int> = wallet.moneyInfo

    override fun spendMoney(money: Money): CoreResult<Unit> = wallet.spendMoney(money)
}