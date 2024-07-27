package view.menu

import view.base.scene.Scene
import view.menu.public_interface.IMenuScene

class MenuScene(controller: MenuController): Scene<MenuSceneState>(controller), IMenuScene {
    override val sceneName: String = "Menu"
    override val operation: String =
        StringBuilder().apply {
            append("a) SHOW WALLET\n")
            append("b) SHOW DRINK\n")
            append("c) BUY DRINK\n")
            append("d) WORK\n")
            append("\n")
            append("e) exit\n")
        }.toString()

    override fun showTitle() {}

    override fun contents(state: MenuSceneState) {}

    override fun showContent() {}
}