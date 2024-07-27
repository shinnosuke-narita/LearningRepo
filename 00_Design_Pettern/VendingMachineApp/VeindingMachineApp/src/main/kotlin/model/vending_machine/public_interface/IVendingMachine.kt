package model.vending_machine.public_interface

import model.customer.public_interface.ICustomer
import model.money.Money
import model.result.CoreResult

interface IVendingMachine {
    fun insertMoney(money: Money, customer: ICustomer): CoreResult<Int?>
    fun getLowestPrice(): Int
    fun getTotalDeposit(): Int
    fun getDepositAmount(money: Money): Int
    fun haveEnoughChange(): CoreResult<Boolean>
}