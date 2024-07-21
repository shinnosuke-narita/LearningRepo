package view.exit

import view.exit.public_interface.IExitRouter
import view.exit.public_interface.IExitScene

class ExitController(
    private val scene: IExitScene,
    private val router: IExitRouter
) {
    companion object {
        private const val EXIT = "a"
        private const val MENU = "b"
    }

    fun nextScene(input: String) {
        when(input) {
            EXIT -> { scene.showExitMessage() }
            MENU -> { router.pushMenuScene() }
            else -> {}
        }
    }
}