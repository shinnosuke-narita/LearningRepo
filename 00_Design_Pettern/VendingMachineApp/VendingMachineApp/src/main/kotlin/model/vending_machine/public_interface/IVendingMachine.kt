package model.vending_machine.public_interface

import model.customer.public_interface.ICustomer
import model.deposit.DepositAmount
import model.money.Money
import model.product.Product
import model.result.CoreResult

interface IVendingMachine {
    fun onPutMoney(money: Money, customer: ICustomer): CoreResult<Int?>
    fun getLowestPrice(): Int
    fun getTotalDeposit(): DepositAmount
    fun getAllProductInfo(): Map<Product, Boolean>
}