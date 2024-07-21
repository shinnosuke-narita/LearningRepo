package domain.buy

import di.PersonProvider
import di.VendingMachineProvider
import domain.buy.mapper.IMoneyMapper
import domain.buy.mapper.MoneyMapper
import domain.buy.model.InputMoney
import domain.buy.model.OutputMoney
import entity.Result
import entity.customer.public_interface.ICustomer
import entity.money.Money
import entity.vending_machine.public_interface.IVendingMachine

class BuyInteractor(
    private val customer: ICustomer = PersonProvider.provide(),
    private val vendingMachine: IVendingMachine = VendingMachineProvider.provide(),
    private val mapper: IMoneyMapper = MoneyMapper()
) : BuyUseCase {
    companion object {
        private const val INVALID_MESSAGE = "無効なお金です"
    }

    override fun putMoney(money: InputMoney): Result<OutputMoney?>  {
        val moneyEntity: Money = mapper.execute(money.value)
            ?: return Result(null, INVALID_MESSAGE)

        val result = customer.putMoney(moneyEntity, vendingMachine)
        return if (result.isError()) {
            Result(null, result.errorMessage)
        } else {
            Result(OutputMoney(result.data!!, money.value))
        }
    }

    override fun selectProduct() {
        // todo "Not yet implemented"
    }
}