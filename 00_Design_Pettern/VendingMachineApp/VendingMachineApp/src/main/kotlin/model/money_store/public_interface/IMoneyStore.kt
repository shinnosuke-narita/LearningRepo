package model.money_store.public_interface

import model.money.Money

interface IMoneyStore {
    fun getAmount(money: Money): Int
    fun add(money: Money)
    fun sub(money: Money)
}
