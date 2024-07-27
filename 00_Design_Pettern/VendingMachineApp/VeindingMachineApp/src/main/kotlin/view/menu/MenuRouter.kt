package view.menu

import core.navigator.Navigator
import view.base.scene.Scene
import view.buy_drink.router.BuyRouter
import view.exit.ExitRouter
import view.menu.public_interface.IMenuRouter

class MenuRouter : IMenuRouter {
    companion object {
        fun setUpMenuScene(): Scene<MenuSceneState> {
            val router = MenuRouter()
            val controller = MenuController(router)
            return MenuScene(controller)
        }
    }

    override fun pushWalletScene() {
//        Navigator.enqueue(WalletRouter.setUpScene())
    }

    override fun pushFinishScene() {
        Navigator.enqueue(ExitRouter.setUpExitScene())
    }

    override fun pushBuyScene() {
        Navigator.enqueue(BuyRouter.setUpBuyScene())
    }
}