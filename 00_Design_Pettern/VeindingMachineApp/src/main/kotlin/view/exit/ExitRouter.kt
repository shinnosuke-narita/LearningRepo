package view.exit

import core.navigator.Navigator
import view.base.scene.Scene
import view.exit.public_interface.IExitRouter
import view.menu.MenuRouter

class ExitRouter: IExitRouter {
    companion object {
        fun setUpExitScene(): Scene {
            val scene = ExitScene()
            val router = ExitRouter()
            val controller = ExitController(scene, router)
            scene.controller = controller

            return scene
        }
    }

    override fun pushMenuScene() {
        Navigator.enqueue(MenuRouter.setUpMenuScene())
    }
}