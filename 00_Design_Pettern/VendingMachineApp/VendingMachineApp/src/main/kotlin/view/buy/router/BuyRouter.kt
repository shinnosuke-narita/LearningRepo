package view.buy.router

import application.customer.GetWalletInfoApplicationService
import application.vending_machine.deposit.GetDepositApplicationService
import application.vending_machine.put_money.PutMoneyApplicationService
import model.navigator.Navigator
import di.PersonProvider
import di.VendingMachineProvider
import view.base.Scene
import view.buy.public_interface.IBuyRouter
import controller.buy.BuyController
import view.buy.BuyScene
import view.home.HomeRouter

class BuyRouter : IBuyRouter {
    companion object {
        fun setUpBuyScene(): Scene<*> {
            val router= BuyRouter()
            val putMoneyApplicationService =
                PutMoneyApplicationService(
                    customer = PersonProvider.provide(),
                    vendingMachine = VendingMachineProvider.provide(),
                )
            val getDepositApplicationService =
                GetDepositApplicationService(
                    VendingMachineProvider.provide()
                )
            val getWalletInfoApplicationService =
                GetWalletInfoApplicationService(
                    PersonProvider.provide()
                )
            val controller =
                BuyController(
                    router = router,
                    putMoneyApplicationService = putMoneyApplicationService,
                    getDepositApplicationService = getDepositApplicationService,
                    getWalletInfoApplicationService = getWalletInfoApplicationService
                )
            val scene = BuyScene(controller)

            return scene
        }
    }

    override fun pushMenu() {
        Navigator.enqueue(HomeRouter.setUpMenuScene())
    }
}