package view.home

import controller.home.HomeController
import view.base.Scene

class HomeScene(controller: HomeController): Scene<HomeSceneState>(controller) {
    override val sceneName: String = "Home画面"
    override val operation: String =
        StringBuilder().apply {
            append("a) BUY DRINK\n")
            append("b) WORK\n")
            append("\n")
            append("c) exit\n")
        }.toString()

    override fun contents(state: HomeSceneState) {}
}