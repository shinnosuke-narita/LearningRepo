package entity.customer.public_interface

import entity.Result
import entity.money.Money

interface IWallet {
    fun spendMoney(money: Money): Result<Unit>
    fun putMoney(money: Money)
    fun getTotal(): Int
}