package view.exit

import core.navigator.Navigator
import view.base.scene.Scene
import view.exit.public_interface.IExitRouter
import view.menu.MenuRouter

class ExitRouter: IExitRouter {
    companion object {
        fun setUpExitScene(): Scene<*> {
            val router = ExitRouter()
            val controller = ExitController(router)
            return ExitScene(controller)
        }
    }

    override fun pushMenuScene() {
        Navigator.enqueue(MenuRouter.setUpMenuScene())
    }
}