package model.customer.public_interface

import model.result.CoreResult
import model.money.Money


interface IWallet {
    val moneyInfo: Map<Money, Int>
    fun spendMoney(money: Money): CoreResult<Unit>
    fun putMoney(money: Money)
    fun getTotal(): Int
}