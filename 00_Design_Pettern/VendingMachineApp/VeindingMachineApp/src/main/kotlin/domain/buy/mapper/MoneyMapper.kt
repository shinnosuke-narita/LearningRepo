package domain.buy.mapper

import entity.money.Money

class MoneyMapper : IMoneyMapper {
    override fun execute(input: Int): Money? {
        return when(input) {
            Money.One.value -> Money.One
            Money.Five.value -> Money.Five
            Money.Ten.value -> Money.Ten
            Money.Fifty.value -> Money.Fifty
            Money.OneHundred.value -> Money.OneHundred
            Money.FiveHundred.value -> Money.FiveHundred
            Money.OneThousand.value -> Money.OneThousand
            Money.FiveThousand.value -> Money.FiveThousand
            Money.TenThousand.value -> Money.TenThousand
            else -> null
        }
    }
}