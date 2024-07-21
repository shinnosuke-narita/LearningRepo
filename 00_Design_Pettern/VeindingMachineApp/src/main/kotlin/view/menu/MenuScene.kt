package view.menu

import view.base.scene.Scene
import view.menu.public_interface.IMenuScene
import entity.money.Money

class MenuScene: Scene(), IMenuScene {
    lateinit var controller: MenuController

    override fun showTitle() {
        println("Menu")
    }

    override fun showContent() {
        showMenu()
    }

    override fun initialFunc() {
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