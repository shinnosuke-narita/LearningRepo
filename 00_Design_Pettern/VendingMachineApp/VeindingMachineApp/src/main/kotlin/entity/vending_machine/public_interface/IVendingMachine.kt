package entity.vending_machine.public_interface

import entity.Result
import entity.money.Money
import entity.customer.public_interface.ICustomer

interface IVendingMachine {
    fun insertMoney(money: Money, customer: ICustomer): Result<Int?>
    fun getLowestPrice(): Int
    fun getTotalDeposit(): Int
    fun getDepositAmount(money: Money): Int
    fun haveEnoughChange(): Result<Boolean>
}