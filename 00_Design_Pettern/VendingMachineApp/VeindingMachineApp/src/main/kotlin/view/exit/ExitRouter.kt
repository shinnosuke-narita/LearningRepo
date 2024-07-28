package view.exit

import controller.exit.ExitController
import model.navigator.Navigator
import view.base.Scene
import view.exit.public_interface.IExitRouter
import view.home.HomeRouter

class ExitRouter: IExitRouter {
    companion object {
        fun setUpExitScene(): Scene<*> {
            val router = ExitRouter()
            val controller = ExitController(router)
            return ExitScene(controller)
        }
    }

    override fun pushMenuScene() {
        Navigator.enqueue(HomeRouter.setUpMenuScene())
    }
}