package application.vending_machine.put_money

import model.customer.public_interface.ICustomer
import model.money.Money
import model.result.CoreResult
import model.vending_machine.public_interface.IVendingMachine

interface IPutMoneyApplicationService {
    fun handle(money: Money): CoreResult<Int?>
}

class PutMoneyApplicationService(
    private val customer: ICustomer,
    private val vendingMachine: IVendingMachine,
) : IPutMoneyApplicationService {
    override fun handle(money: Money): CoreResult<Int?> =
        customer.spendMoney(money).let { result ->
            if (result.isError())
                CoreResult(null, result.errorMessage)
            else
                vendingMachine.onPutMoney(money, customer)
        }
}