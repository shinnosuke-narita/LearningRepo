package entity.customer.public_interface

import entity.Result
import entity.money.Money
import entity.vending_machine.public_interface.IVendingMachine

interface ICustomer {
    fun putMoney(
        money: Money,
        vendingMachine: IVendingMachine
    ): Result<Int?>

    fun returnMoney(money: Money)
}