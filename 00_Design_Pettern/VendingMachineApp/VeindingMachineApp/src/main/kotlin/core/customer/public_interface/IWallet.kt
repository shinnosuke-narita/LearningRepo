package core.customer.public_interface

import core.result.CoreResult
import core.money.Money


interface IWallet {
    val moneyInfo: Map<Money, Int>
    fun spendMoney(money: Money): CoreResult<Unit>
    fun putMoney(money: Money)
    fun getTotal(): Int
}