package core.deposit.public_interface

import core.money.Money
import core.result.CoreResult
import core.vending_machine.public_interface.IVendingMachine

interface IDeposit {
    fun insertMoney(money: Money, vendingMachine: IVendingMachine): CoreResult<Int?>
    fun getTotal(): Int
    fun getAmount(money: Money): Int
}