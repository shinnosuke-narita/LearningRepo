package view.menu

import view.base.scene.Scene
import view.menu.public_interface.IMenuScene
import core.money.Money
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MenuScene: Scene(), IMenuScene {
    lateinit var controller: MenuController

    override fun showTitle() {
        println("Menu")
    }

    override fun showContent() {
        showMenu()
    }

    override fun startCollect(): Job =
        sceneScope.launch {
            controller.nextScene(readln())
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