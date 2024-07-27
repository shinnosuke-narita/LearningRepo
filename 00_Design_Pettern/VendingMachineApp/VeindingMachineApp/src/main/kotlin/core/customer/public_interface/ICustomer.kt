package core.customer.public_interface

import core.money.Money
import core.vending_machine.public_interface.IVendingMachine
import core.result.CoreResult


interface ICustomer {
    fun putMoney(
        money: Money,
        vendingMachine: IVendingMachine
    ): CoreResult<Int?>


    fun returnMoney(money: Money)

    fun getWalletInfo(): Map<Money, Int>
}