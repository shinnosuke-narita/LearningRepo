package application.customer

import model.customer.public_interface.ICustomer
import model.money.Money

interface IGetWalletInfoApplicationService {
    fun handle(): Map<Money, Int>
}

class GetWalletInfoApplicationService(
    private val customer: ICustomer
) : IGetWalletInfoApplicationService {
    override fun handle(): Map<Money, Int> = customer.getWalletInfo()
}