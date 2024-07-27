package view.show_wallet

import core.customer.Customer
import core.customer.public_interface.ICustomer
import view.show_wallet.public_interface.IWalletRouter
import view.show_wallet.public_interface.IWalletScene

class WalletController(
    private val person: core.customer.public_interface.ICustomer,
    private var scene: IWalletScene,
    private val walletRouter: IWalletRouter
) {
    fun requestWalletData() {
        //person.collectMoneyData(scene::showMoneyIndividual)
        //scene.showTotalMoney(person.getTotal())
        walletRouter.pushMenuScene()
    }
}