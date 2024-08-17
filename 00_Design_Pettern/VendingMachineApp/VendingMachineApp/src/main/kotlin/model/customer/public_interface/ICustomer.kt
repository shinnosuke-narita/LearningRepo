package model.customer.public_interface

import model.money.Money
import model.result.CoreResult

interface ICustomer {
    fun receiveMoney(money: Money)

    fun getWalletInfo(): Map<Money, Int>

    fun spendMoney(money: Money): CoreResult<Unit>
}