package application.vending_machine.deposit

import model.deposit.DepositAmount
import model.deposit.IDeposit

interface IGetDepositApplicationService {
    fun handle(): DepositAmount
}

class GetDepositApplicationService(
    private val deposit: IDeposit
) : IGetDepositApplicationService {
    override fun handle(): DepositAmount = deposit.currentDeposit
}

