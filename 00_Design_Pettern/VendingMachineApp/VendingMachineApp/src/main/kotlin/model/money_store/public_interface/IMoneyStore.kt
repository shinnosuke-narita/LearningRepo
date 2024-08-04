package model.money_store.public_interface

import model.money.Money
import model.money_store.Deposit
import model.result.CoreResult


interface IMoneyStore {
    val deposit: Deposit
    fun putMoney(money: Money, lowestProductPrice: Int): CoreResult<Deposit?>
}
