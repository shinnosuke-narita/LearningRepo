package entity.counter

import entity.money.Money

class MoneyCounter(
    money: Money,
    amount: Int
) : Counter<Money>(money, amount) {
    val money: Money get() = data

    override fun isSame(data: Money): Boolean {
        return this.money.value == data.value
    }

    fun getTotal(): Int = money.value * _amount
}