package view.menu

import view.base.scene.Scene
import view.menu.public_interface.IMenuScene
import core.money.Money
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MenuScene(controller: MenuController): Scene<MenuSceneState>(controller), IMenuScene {
    override val sceneName: String = "Menu"

    override fun showTitle() {
        println("Menu")
    }

    override fun showContent() {
        showMenu()
    }

    override suspend fun startCollect() {
//        controller.nextScene(readln())
    }

    private fun showMenu() {
        println("a) SHOW WALLET")
        println("b) SHOW DRINK")
        println("c) BUY DRINK")
        println("d) WORK")
        println()
        println("e) exit")
        println()
    }
}