package view.buy_drink

import core.navigator.Navigator
import view.base.scene.Scene
import view.buy_drink.public_interface.IBuyRouter
import domain.buy.BuyInteractor
import view.menu.MenuRouter

class BuyRouter : IBuyRouter {
    companion object {
        fun setUpBuyScene(): Scene {
            val router= BuyRouter()
            val scene = BuyScene()
            val interactor = BuyInteractor()
            val controller = BuyController(router, scene, interactor)
            scene.controller = controller

            return scene
        }
    }

    override fun pushMenu() {
        Navigator.enqueue(MenuRouter.setUpMenuScene())
    }
}