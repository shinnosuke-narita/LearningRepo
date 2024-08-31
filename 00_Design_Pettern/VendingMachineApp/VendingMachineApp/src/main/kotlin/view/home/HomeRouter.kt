package view.home

import application.vending_machine.product.GetProductInfoApplicationService
import controller.home.HomeController
import di.ServiceLocator
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
                    GetProductInfoApplicationService(ServiceLocator.productStock)
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