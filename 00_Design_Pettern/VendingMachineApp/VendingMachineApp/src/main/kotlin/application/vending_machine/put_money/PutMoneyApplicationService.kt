package application.vending_machine.put_money

import model.customer.public_interface.ICustomer
import model.deposit.DepositAmount
import model.money.Money
import model.result.CoreResult
import model.services.deposit.IDepositService

interface IPutMoneyApplicationService {
    fun handle(money: Money): CoreResult<DepositAmount?>
}

class PutMoneyApplicationService(
    private val customer: ICustomer,
    private val depositService: IDepositService,
) : IPutMoneyApplicationService {
    override fun handle(money: Money): CoreResult<DepositAmount?> =
        customer.spendMoney(money).let { result ->
            if (result.isError())
                CoreResult(null, result.errorMessage)
            else
                depositService.handleDeposit(money, customer)
        }
}