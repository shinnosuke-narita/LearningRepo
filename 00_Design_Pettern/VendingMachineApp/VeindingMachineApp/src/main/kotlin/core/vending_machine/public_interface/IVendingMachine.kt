package core.vending_machine.public_interface

import core.customer.public_interface.ICustomer
import core.money.Money
import core.result.CoreResult

interface IVendingMachine {
    fun insertMoney(money: Money, customer: ICustomer): CoreResult<Int?>
    fun getLowestPrice(): Int
    fun getTotalDeposit(): Int
    fun getDepositAmount(money: Money): Int
    fun haveEnoughChange(): CoreResult<Boolean>
}