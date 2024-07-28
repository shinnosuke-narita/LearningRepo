package view.home

import controller.home.HomeController
import di.VendingMachineProvider
import model.navigator.Navigator
import view.base.Scene
import view.buy.router.BuyRouter
import view.exit.ExitRouter
import view.home.public_interface.IHomeRouter

class HomeRouter : IHomeRouter {
    companion object {
        fun setUpMenuScene(): Scene<HomeSceneState> {
            val controller =
                HomeController(
                    HomeRouter(),
                    VendingMachineProvider.provide()
                )
            return HomeScene(controller)
        }
    }

    override fun pushFinishScene() {
        Navigator.enqueue(ExitRouter.setUpExitScene())
    }

    override fun pushBuyScene() {
        Navigator.enqueue(BuyRouter.setUpBuyScene())
    }
}