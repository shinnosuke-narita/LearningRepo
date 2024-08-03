package model.deposit.public_interface

import model.money.Money
import model.result.CoreResult
import model.vending_machine.public_interface.IVendingMachine

interface IDeposit {
    fun insertMoney(money: Money, vendingMachine: IVendingMachine): CoreResult<Int?>
    fun getTotal(): Int
    fun getAmount(money: Money): Int
}