package view.menu

import core.navigator.Navigator
import view.base.scene.Scene
import view.buy_drink.BuyRouter
import view.exit.ExitRouter
import view.menu.public_interface.IMenuRouter
import view.show_wallet.WalletRouter

class MenuRouter : IMenuRouter {
    companion object {
        fun setUpMenuScene(): Scene {
            val router = MenuRouter()
            val scene = MenuScene()
            val controller = MenuController(router, scene)
            scene.controller = controller
            return scene
        }
    }

    override fun pushWalletScene() {
        Navigator.enqueue(WalletRouter.setUpScene())
    }

    override fun pushFinishScene() {
        Navigator.enqueue(ExitRouter.setUpExitScene())
    }

    override fun pushBuyScene() {
        Navigator.enqueue(BuyRouter.setUpBuyScene())
    }
}