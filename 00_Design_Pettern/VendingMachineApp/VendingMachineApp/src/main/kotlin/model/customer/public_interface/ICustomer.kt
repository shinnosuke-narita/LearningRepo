package model.customer.public_interface

import model.money.Money
import model.vending_machine.public_interface.IVendingMachine
import model.result.CoreResult


interface ICustomer {
    fun putMoney(
        money: Money,
        vendingMachine: IVendingMachine
    ): CoreResult<Int?>


    fun receiveMoney(money: Money)

    fun getWalletInfo(): Map<Money, Int>
}