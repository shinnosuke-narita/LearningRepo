package view.exit

import view.base.scene.Scene
import view.exit.public_interface.IExitScene

class ExitScene(controller: ExitController) : Scene<ExitSceneState>(controller), IExitScene {
    override val sceneName: String = "アプリ終了"
    override fun showTitle() {
        println("++++++++ アプリ終了 ++++++++")
    }

    override fun showContent() {
        println("a) 終了")
        println("b) メニューに戻る")
        println()
    }

    override suspend fun startCollect() {
//        controller.nextScene(readln())
    }

    override fun showExitMessage() {
        println("アプリを終了しました")
    }
}