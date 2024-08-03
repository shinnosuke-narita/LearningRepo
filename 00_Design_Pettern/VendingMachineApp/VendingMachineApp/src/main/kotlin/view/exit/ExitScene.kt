package view.exit

import controller.exit.ExitController
import controller.exit.ExitSceneState
import view.base.Scene

class ExitScene(controller: ExitController) : Scene<ExitSceneState>(controller) {
    override val sceneName: String = "アプリ終了"
    override val operation: String =
        StringBuilder().apply {
            append("a) 終了\n")
            append("b) メニューに戻る")
        }.toString()

    override fun contents(state: ExitSceneState) {}
}