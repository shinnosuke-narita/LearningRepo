package view.buy_drink.router

import core.navigator.Navigator
import di.PersonProvider
import di.VendingMachineProvider
import view.base.Scene
import view.buy_drink.public_interface.IBuyRouter
import view.buy_drink.BuyController
import view.buy_drink.BuyScene
import view.menu.MenuRouter

class BuyRouter : IBuyRouter {
    companion object {
        fun setUpBuyScene(): Scene<*> {
            val router= BuyRouter()
            val controller =
                BuyController(
                    router = router,
                    customer = PersonProvider.provide(),
                    vendingMachine = VendingMachineProvider.provide(),
                )
            val scene = BuyScene(controller)

            return scene
        }
    }

    override fun pushMenu() {
        Navigator.enqueue(MenuRouter.setUpMenuScene())
    }
}