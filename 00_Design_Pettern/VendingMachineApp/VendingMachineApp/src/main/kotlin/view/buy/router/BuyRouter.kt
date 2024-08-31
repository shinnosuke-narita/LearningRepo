package view.buy.router

import application.customer.GetWalletInfoApplicationService
import application.vending_machine.deposit.GetDepositApplicationService
import application.vending_machine.put_money.PutMoneyApplicationService
import model.navigator.Navigator
import view.base.Scene
import view.buy.public_interface.IBuyRouter
import controller.buy.BuyController
import di.ServiceLocator
import view.buy.BuyScene
import view.home.HomeRouter

class BuyRouter : IBuyRouter {
    companion object {
        fun setUpBuyScene(): Scene<*> {
            val putMoneyApplicationService =
                PutMoneyApplicationService(
                    customer = ServiceLocator.customer,
                    depositService = ServiceLocator.depositService,
                )
            val getDepositApplicationService = GetDepositApplicationService(ServiceLocator.deposit)
            val getWalletInfoApplicationService = GetWalletInfoApplicationService(ServiceLocator.customer)

            val controller =
                BuyController(
                    router = BuyRouter(),
                    putMoneyApplicationService = putMoneyApplicationService,
                    getDepositApplicationService = getDepositApplicationService,
                    getWalletInfoApplicationService = getWalletInfoApplicationService
                )

            return BuyScene(controller)
        }
    }

    override fun pushMenu() {
        Navigator.enqueue(HomeRouter.setUpMenuScene())
    }
}