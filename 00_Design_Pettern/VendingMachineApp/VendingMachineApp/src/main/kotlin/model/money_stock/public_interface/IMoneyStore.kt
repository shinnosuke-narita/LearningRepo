package model.money_stock.public_interface

import model.money.Money
import model.money_stock.Deposit
import model.result.CoreResult


interface IMoneyStore {
    val deposit: Deposit
    fun putMoney(money: Money, lowestProductPrice: Int): CoreResult<Deposit?>
}
