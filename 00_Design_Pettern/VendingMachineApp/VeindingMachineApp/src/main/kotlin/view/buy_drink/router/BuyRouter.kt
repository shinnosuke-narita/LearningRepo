package view.buy_drink.router

import core.navigator.Navigator
import di.PersonProvider
import di.VendingMachineProvider
import view.base.scene.Scene
import view.buy_drink.public_interface.IBuyRouter
import view.buy_drink.BuyController
import view.buy_drink.BuyScene
import view.menu.MenuRouter

class BuyRouter : IBuyRouter {
    companion object {
        fun setUpBuyScene(): Scene {
            val router= BuyRouter()
            val scene = BuyScene()
            val controller =
                BuyController(
                    router = router,
                    scene = scene,
                    customer = PersonProvider.provide(),
                    vendingMachine = VendingMachineProvider.provide(),
                )
            scene.controller = controller

            return scene
        }
    }

    override fun pushMenu() {
        Navigator.enqueue(MenuRouter.setUpMenuScene())
    }
}