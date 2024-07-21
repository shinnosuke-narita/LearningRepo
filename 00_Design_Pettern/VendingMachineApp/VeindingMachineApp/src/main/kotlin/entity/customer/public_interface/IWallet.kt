package entity.customer.public_interface

import entity.Result
import entity.money.Money

interface IWallet {
    fun getMoney(money: Money): Result<Money?>
    fun addMoney(money: Money)
}