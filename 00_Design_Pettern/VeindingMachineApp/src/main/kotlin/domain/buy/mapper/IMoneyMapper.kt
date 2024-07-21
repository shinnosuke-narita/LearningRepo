package domain.buy.mapper

import entity.money.Money

interface IMoneyMapper {
    fun execute(input: Int): Money?
}