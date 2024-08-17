package application.vending_machine.deposit

import model.deposit.DepositAmount
import model.vending_machine.public_interface.IVendingMachine

interface IGetDepositApplicationService {
    fun handle(): DepositAmount
}

class GetDepositApplicationService(
    private val vendingMachine: IVendingMachine
) : IGetDepositApplicationService {
    override fun handle(): DepositAmount = vendingMachine.getTotalDeposit()
}

