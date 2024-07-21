package view.exit

import view.base.scene.Scene
import view.exit.public_interface.IExitScene

class ExitScene : Scene(), IExitScene {
    lateinit var controller: ExitController

    override fun showTitle() {
        println("++++++++ アプリ終了 ++++++++")
    }

    override fun showContent() {
        println("a) 終了")
        println("b) メニューに戻る")
        println()

    }

    override fun initialFunc() {
        controller.nextScene(readln())
    }

    override fun showExitMessage() {
        println("アプリを終了しました")
    }
}