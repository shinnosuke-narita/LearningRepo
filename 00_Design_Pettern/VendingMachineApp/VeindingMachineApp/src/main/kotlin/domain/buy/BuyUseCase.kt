package domain.buy

import domain.buy.model.InputMoney
import domain.buy.model.OutputMoney
import entity.Result

interface BuyUseCase {
    fun putMoney(money: InputMoney): Result<OutputMoney?>
    fun selectProduct()
}