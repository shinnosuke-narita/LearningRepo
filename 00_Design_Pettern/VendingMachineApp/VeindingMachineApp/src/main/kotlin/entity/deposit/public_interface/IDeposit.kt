package entity.deposit.public_interface

import entity.Result
import entity.money.Money
import entity.vending_machine.public_interface.IVendingMachine

interface IDeposit {
    fun insertMoney(money: Money, vendingMachine: IVendingMachine): Result<Int?>
    fun getTotal(): Int
    fun getAmount(money: Money): Int
}