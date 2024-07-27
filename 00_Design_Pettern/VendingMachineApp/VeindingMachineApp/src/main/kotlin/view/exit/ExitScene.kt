package view.exit

import view.base.Scene
import view.exit.public_interface.IExitScene

class ExitScene(controller: ExitController) : Scene<ExitSceneState>(controller), IExitScene {
    override val sceneName: String = "アプリ終了"
    override val operation: String =
        StringBuilder().apply {
            append("a) 終了\n")
            append("b) メニューに戻る")
        }.toString()

    override fun contents(state: ExitSceneState) {}

    override fun showExitMessage() {
        println("アプリを終了しました")
    }
}